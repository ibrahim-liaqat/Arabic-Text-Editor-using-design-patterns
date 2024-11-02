package DAL;

public class SQLdata extends AbstractDAOfactory{

	@Override
	public sqlinterface createsql() {
		return new sql();
	}

	

}
