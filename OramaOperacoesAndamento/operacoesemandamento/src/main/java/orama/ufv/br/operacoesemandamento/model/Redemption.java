package orama.ufv.br.operacoesemandamento.model;

/**
 * Created by usuario on 24/08/2017.
 */

public class Redemption {
    SubAccounts subAccounts;
    String redemptionDate;
    float value;
    String status;
    String conversionDate;
    String paymentInSubAccount;
    String anticipated;
    String requestDate;
    boolean isCancellable;

    public Redemption(SubAccounts subAccounts, String redemptionDate, float value, String status, String conversionDate, String paymentInSubAccount, String anticipated, String requestDate, boolean isCancellable) {
        this.subAccounts = subAccounts;
        this.redemptionDate = redemptionDate;
        this.value = value;
        this.status = status;
        this.conversionDate = conversionDate;
        this.paymentInSubAccount = paymentInSubAccount;
        this.anticipated = anticipated;
        this.requestDate = requestDate;
        this.isCancellable = isCancellable;
    }

    public SubAccounts getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(SubAccounts subAccounts) {
        this.subAccounts = subAccounts;
    }

    public String getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(String redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(String conversionDate) {
        this.conversionDate = conversionDate;
    }

    public String getPaymentInSubAccount() {
        return paymentInSubAccount;
    }

    public void setPaymentInSubAccount(String paymentInSubAccount) {
        this.paymentInSubAccount = paymentInSubAccount;
    }

    public String getAnticipated() {
        return anticipated;
    }

    public void setAnticipated(String anticipated) {
        this.anticipated = anticipated;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }

}
