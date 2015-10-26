package bankApplication;

import java.util.Comparator;

public class AccountDateOpenedComparator implements Comparator<Account>{

	@Override
	public int compare(Account a1, Account a2) {
		return a1.getDateOpened().compareTo(a2.getDateOpened());
	}

}
