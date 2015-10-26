package bankApplication;

import java.util.Comparator;

public class AccountOwnerComparator implements Comparator<Account>{

	@Override
	public int compare(Account a1, Account a2) {
		return a1.getAccountOwner().compareTo(a2.getAccountOwner());
	}

}
