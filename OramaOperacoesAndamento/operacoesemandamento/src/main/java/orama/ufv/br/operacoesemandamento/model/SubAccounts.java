package orama.ufv.br.operacoesemandamento.model;


import java.util.List;

/**
 * Created by usuario on 22/08/2017.
 */

public class SubAccounts {
    private String name;
    private Account account;
    private List<WithdrawalCc> withdrawalCcs;
    private List<BuyFixedIncome> buyFixedIncomes;
    private List<ApplicationFunds> applicationFundses;
    private List<Redemption> redemptions;

    public SubAccounts(String name, Account account){
        this.name = name;
        this.account = account;
    }

    public List<Redemption> getRedemptions() {
        return redemptions;
    }

    public void setRedemptions(List<Redemption> redemptions) {
        this.redemptions = redemptions;
    }

    public List<ApplicationFunds> getApplicationFundses() {
        return applicationFundses;
    }

    public void setApplicationFundses(List<ApplicationFunds> applicationFundses) {
        this.applicationFundses = applicationFundses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<WithdrawalCc> getWithdrawalCcs() {
        return withdrawalCcs;
    }

    public void setWithdrawalCcs(List<WithdrawalCc> withdrawalCcs) {
        this.withdrawalCcs = withdrawalCcs;
    }

    public List<BuyFixedIncome> getBuyFixedIncomes() {
        return buyFixedIncomes;
    }

    public void setBuyFixedIncomes(List<BuyFixedIncome> buyFixedIncomes) {
        this.buyFixedIncomes = buyFixedIncomes;
    }

}
