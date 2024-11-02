package mian;

import BL.filemain;
import BL.filemaininterface;
import DAL.AbstractDAOfactory;
import DAL.sql;
import DAL.sqlinterface;
import PL.home;

public class mainclass {
	 public static void main(String args[]) {
	        sqlinterface data=AbstractDAOfactory.getInstance().createsql();
	        filemaininterface logic=new filemain(data);
	        new home(logic);
	    }
}
