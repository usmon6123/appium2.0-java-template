package org.example.phone1;

import org.example.modul.WithdrawalModule;
import org.example.sqlQuery.Query;

import static org.example.constants.BaseConstants.*;
import static org.example.constants.TransferConstants.*;

public class Transfer extends Helper {
    private static final Query query = new Query();

    public int transferMain(WithdrawalModule withdrawal) throws InterruptedException {

        //kard girisde hatalik varsa,ise tamamlayacak ve comment = "Kart nömrəsi yanlışdır" diyecek
        if (!kardGiris(withdrawal)) {
            //sqle giderek isi tamamliyor ve comment = "Kart nömrəsi yanlışdır" ve result = 0 olarak guncelliyor;
            query.updateStatusResultCommentById(finished, 0, fake_card, 0.0, withdrawal.getId());
            System.out.println("Kard bilgilerini giremedi");
            return 1;
        }

        if (!tutarGiris(withdrawal)) {
            System.out.println("Tutar giremedi");
            //sqle giderek isi baskasi almasi icin robotun bilgilerini siliyor
            query.updateRobotNull(withdrawal.getId());
            return 1;
        }

//                //burasini guncellemek gerek
//                if (!result(withdrawal)) {
//                    query.updateStatusResultCommentById(FINISHED, 0, FAILED, 0.0, withdrawal.getId());
//                }
//                query.updateStatusResultCommentById(FINISHED, 1, SUCCESS, withdrawal.getAmount(), withdrawal.getId());
        return 0;
    }



    private boolean kardGiris(WithdrawalModule withdrawal) {

        try {
            click(transfer);
            Thread.sleep(30000);
            click(transferToCard);
            Thread.sleep(30000);
            click(cardNumberBar);
            Thread.sleep(30000);
            click(cardNumberByTransfer);
            String cardNum = withdrawal.getCardNumber();

            if (cardNum.length() != 16) {
                click(backCardNumber);
                return false;
            }
            sendMobileKeys(cardNumberByTransfer, cardNum);

            if (checkedByVisual(invalidCardNumber)) {
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

    private boolean result(WithdrawalModule withdrawal) {
        try {

        click(continueTransfer);

        return  true;
        }catch (Exception e) {return false;}

    }


}


