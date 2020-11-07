package orama.ufv.br.operacoesemandamento.model;

/**
 * Created by usuario on 24/08/2017.
 */

public class ApplicationFunds {
    SubAccounts subAccounts;
    String aplicationDate;
    float value;
    String status;
    String requestDate;
    String quotaDate;
    String funds;
    boolean isCancellable;

    public ApplicationFunds(SubAccounts subAccounts, String aplicationDate, float value, String status, String requestDate, String quotaDate, boolean isCancellable, String funds) {
        this.subAccounts = subAccounts;
        this.aplicationDate = aplicationDate;
        this.value = value;
        this.status = status;
        this.requestDate = requestDate;
        this.quotaDate = quotaDate;
        this.isCancellable = isCancellable;
        this.funds = funds;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public SubAccounts getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(SubAccounts subAccounts) {
        this.subAccounts = subAccounts;
    }

    public String getAplicationDate() {
        return aplicationDate;
    }

    public void setAplicationDate(String aplicationDate) {
        this.aplicationDate = aplicationDate;
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

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getQuotaDate() {
        return quotaDate;
    }

    public void setQuotaDate(String quotaDate) {
        this.quotaDate = quotaDate;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }
}
