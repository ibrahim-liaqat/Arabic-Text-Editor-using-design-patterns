package DAL;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractDAOfactory implements DAOFactory {

    private static DAOFactory instance = null;

    public static DAOFactory getInstance() {
        if (instance == null) {
            synchronized (AbstractDAOfactory.class) {  
                if (instance == null) {
                    String factoryClassName = null;
                    try (FileInputStream input = new FileInputStream("config.properties")) {
                        Properties prop = new Properties();
                        prop.load(input);
                        factoryClassName = prop.getProperty("dao.factory.class");

                        
                        Class<?> clazz = Class.forName(factoryClassName);
                        instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

}
