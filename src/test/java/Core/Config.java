package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {
    protected static FileInputStream fileInputStream;
    protected static Properties properties;

    static {
        try {
            //путь до файла с настройками
            fileInputStream = new FileInputStream("properties\\browser.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public boolean loadFromFile(String path) {
        boolean result = true;
        try {
            this.properties = new Properties();
            FileInputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is, "cp1251");
            this.properties.load(isr);
        } catch (FileNotFoundException var) {
            result = false;
            System.out.println("Указанный файл конфигурации [" + path + "] не найден.");
        } catch (IOException var) {
            result = false;
            System.out.println("Не могу открыть файл [" + path + "].");
        }
        return result;
    }
    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
