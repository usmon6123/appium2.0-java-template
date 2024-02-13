package org.example.phone1;

import org.example.modul.WithdrawalModule;
import org.example.sqlQuery.Query;

import static org.example.constants.BaseConstants.*;
import static org.example.constants.TransferConstants.*;

public class Transfer extends Helper {

    public void transferMain() throws InterruptedException {

        Query query = new Query();
        while (true) {
            click(defaultClickInHomePage);
            Thread.sleep(500);
            int status = query.getRobotStatus();
            //TRANSFER
            if (status == 2) {

//                double amount = getCurrentAmount(currentAmount, query);
//                if (amount == 0.0) {
//                    continue;
//                }

                WithdrawalModule withdrawal = query.islemAl(100.0);//amount
                if (withdrawal == null) {
                    continue;
                }

                //kard girisde hatalik varsa,ise tamamlayacak ve comment = "Kart nömrəsi yanlışdır" diyecek
                if (!kardGiris(withdrawal)) {
                    //sqle giderek isi tamamliyor ve comment = "Kart nömrəsi yanlışdır" ve result = 0 olarak guncelliyor;
                    query.updateStatusResultCommentById(FINISHED, 0, FAKE_CARD, 0.0, withdrawal.getId());
                    System.out.println("Kard bilgilerini giremedi");
                    continue;
                }


                if (!tutarGiris(withdrawal)) {
                    System.out.println("Tutar giremedi");
                    //sqle giderek isi baskasi almasi icin robotun bilgilerini siliyor
                    query.updateRobotNull(withdrawal.getId());
                    continue;
                }

//                //burasini guncellemek gerek
//                if (!result(withdrawal)) {
//                    query.updateStatusResultCommentById(FINISHED, 0, FAILED, 0.0, withdrawal.getId());
//                }
//                query.updateStatusResultCommentById(FINISHED, 1, SUCCESS, withdrawal.getAmount(), withdrawal.getId());
            }
        }
    }

    private boolean kardGiris(WithdrawalModule withdrawal) {

        try {
            click(transfer);
            click(transferToCard);
            click(cardNumberBar);
            click(cardNumberByTransfer);
            String cardNum = withdrawal.getCard_no();

            if (cardNum.length() != 16) {
                click(backCardNumber);
                return false;
            }
            sendMobileKeys(cardNumberByTransfer, cardNum);

            if (checkedByVisual(invalidCardNumber)){
                click(backCardNumber);
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
                    click(backBalance);
                    click(backCardNumber);
                    return false;
                }
            }

            if (checkedByVisual(balancePage)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean tutarGiris(WithdrawalModule withdrawal) {
        try {
            //amounti girecek
            if (checkedByVisual(balancePage)) {
                click(balanceTransfer);
                sendPressKey(balanceTransfer, String.valueOf(withdrawal.getAmount()));

                //bakiye yetersisse ana sayfaya geri donecek
                if (checkedByVisual(balanceError)) {
                    click(backBalance);
                    if (checkedByVisual(ExpDatePage)) click(backBalance);
                    click(backCardNumber);
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

//    private boolean result(WithdrawalModule withdrawal) {
//        try {
//            if (checked(continueTransfer)) {
//                click(continueTransfer);
//                return true;
//            }
//            ;
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }





}


