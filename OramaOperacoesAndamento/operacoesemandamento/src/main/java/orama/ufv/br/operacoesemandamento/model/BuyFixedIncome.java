package orama.ufv.br.operacoesemandamento.model;

/**
 * Created by usuario on 22/08/2017.
 */

public class BuyFixedIncome {
    private SubAccounts subAccounts;
    private String buyDate;
    private String title;
    private float value;
    private String requestDate;
    private String status;
    private boolean isCancellable;

    public BuyFixedIncome(SubAccounts subAccounts, String buyDate, String title, float value, String requestDate, String status, boolean isCancellable) {
        this.subAccounts = subAccounts;
        this.buyDate = buyDate;
        this.title = title;
        this.value = value;
        this.requestDate = requestDate;
        this.status = status;
        this.isCancellable = isCancellable;
    }

    public SubAccounts getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(SubAccounts subAccounts) {
        this.subAccounts = subAccounts;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }
}
