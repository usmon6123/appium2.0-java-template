package org.example.phone3;

import org.example.modul.WithdrawalModule;
import org.example.sqlQuery.Query;

import static org.example.constants.BaseConstants.*;
import static org.example.constants.TransferConstants.*;
import static org.example.phone3.MainTest.currentAmount;
import static org.example.phone3.MainTest.getCurrentAmount;

public class Transfer extends Helper {
    private static final Query query = new Query();

    public void transferMain(WithdrawalModule withdrawal) throws InterruptedException {

        //kard girisde hatalik varsa,ise tamamlayacak ve comment = "Kart nömrəsi yanlışdır" diyecek
        if (!kardGiris(withdrawal)) {
            //sqle giderek isi tamamliyor ve comment = "Kart nömrəsi yanlışdır" ve result = 0 olarak guncelliyor;
            query.updateStatusResultCommentById(finished, 0, FAKE_CARD, 0.0, withdrawal.getId());
            System.out.println("Kard bilgileri yanlis");
            statusE = 0;
            return;
        }

        if (!tutarGiris(withdrawal)) {
            System.out.println("Tutar giremedi");
            //sqle giderek isi baskasi almasi icin robotun bilgilerini siliyor
            query.updateRobotNull(withdrawal.getId());
            System.out.println(withdrawal.getId());
            goHome();
            return;
        }

        //burasini guncellemek gerek
        if (!result(withdrawal)) {
            query.updateStatusResultCommentById(finished, 0, FAILED, 0.0, withdrawal.getId());
        } else query.updateStatusResultCommentById(finished, 1, SUCCESS, withdrawal.getAmount(), withdrawal.getId());
        return;
    }


    private boolean kardGiris(WithdrawalModule withdrawal) {


        click(transfer);
        click(transferToCard);
        click(cardNumberBar);
        click(cardNumberByTransfer);
        String cardNum = withdrawal.getCardNumber();

        if (cardNum.length() != 16) {
            goHome();
            return false;
        }
        sendMobileKeys(cardNumberByTransfer, cardNum);

        if (checkedByVisual(invalidCardNumber)) {
            goHome();
            return false;
        }
        click(continueTransfer);

        //expired date sayfasi acilsa burasina girecek
        if (checkedByVisual(ExpDatePage)) {
            click(ExpDate);
            sendMobileKeys(ExpDate, withdrawal.getExpiry_date());
            click(continueTransfer);
            //expired date yanlissa ana sayfaya geri donecek
            if (!checkedByVisual(balancePage)) {
                goHome();
                return false;
            }
        }

        return checkedByVisual(balancePage);
    }

    private boolean tutarGiris(WithdrawalModule withdrawal) {
        try {
            //amounti girecek
            if (checkedByVisual(balancePage)) {
                click(balanceTransfer);
                sendPressKey(balanceTransfer, String.valueOf(withdrawal.getAmount()));

                //bakiye yetersisse ana sayfaya geri donecek
                if (checkedByVisual(balanceError)) {
                    goHome();
                    return false;
                }
                return true;
            }
            //amount sayfayi bulamassa su anda isi birakiyor-> burasini guncellemek gerek
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean result(WithdrawalModule withdrawal) {
        try {
            int controlStatus = 2;
            //transfer tusunu bulursa direk tiklayacak
            if (checkedByVisual(findByViewView(String.valueOf(withdrawal.getAmount())))) {
                click(findByViewView(String.valueOf(withdrawal.getAmount())));

                //transaction bittikten sonra ana sayfaya geri donuyor
                if (checkedByVisual(backToHomeResult) && checkedByVisual(transferInProgress)) {
                    click(backToHomeResult);

                    //ana sayfaya geldikden sonra bakiyenin kontrolini ediyor
                    if (checkedByVisual(amount)) {
                        for (int i = 0; i < 4; i++) {
                            Thread.sleep(2000);
                            swipe();
                            if (getCurrentAmount() == currentAmount - withdrawal.getAmount()){
                                controlStatus =  controlTransfer(withdrawal.getCardNumber(), String.valueOf(withdrawal.getAmount()));
                                if (controlStatus == 1)return true;
                            }
                        }
                        controlStatus =  controlTransfer(withdrawal.getCardNumber(), String.valueOf(withdrawal.getAmount()));
                        if (controlStatus == 0)return false;
                        if (controlStatus == 2)return frameYesNo(controlTransferMessage(withdrawal.getCardNumber(),withdrawal.getAmount()));
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}


