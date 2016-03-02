import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Мария on 01.03.16.
 */
public class JTest
{
	public  static WebDriver driver;

	@BeforeClass
	public static void Init(){
		driver = new ChromeDriver();
		driver.get("http://2gis.ru/countries/global");
	}

	@Test
	//реализация Тест-кейса №1
	public void CorrectSearch(){

		//задаем список городов, которые должны быть в базе
		String[] towns = {"Хабаровск", "Новосибирск", "Москва", "Санкт-Петербург", "Самара", "Красноярск", "Омск", "Томск"};
		WebElement input = driver.findElement(By.xpath("//*[@id=\"module-1-13\"]/div[1]/div/input"));

		for(int i = 0; i < towns.length; i++){

			input.sendKeys(towns[i]);
			WebElement wait = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/*//*[@id=\"module-1-13\"]/div[2]/div/div[2]/section/ul/li/h2/a/span")));
			WebElement answer = driver.findElement(By.xpath("/*//*[@id=\"module-1-13\"]/div[2]/div/div[2]/section/ul/li/h2/a/span"));
			assertEquals(answer.getText(), towns[i]);
			input.clear();
		}
	}


	@Test
	//реализация Тест-кейса №2
	public void CorrectList(){

		List<WebElement> countries = driver.findElements(By.xpath("//*[@id=\"module-1-13\"]/div[2]/div/div[2]/section"));

		for(int i = 1; i <= countries.size(); i++){

			WebElement county_elem = driver.findElement(By.xpath("//*[@id=\"module-1-13\"]/div[2]/div/div[2]/section["+i+"]/header/div/span"));

			// сколько городов написано в () после названия страны
			int claimed_amount = Integer.parseInt(county_elem.getText());

			//сколько на самом деле городов выпало в списке
			List<WebElement> towns = driver.findElements(By.xpath("//*[@id=\"module-1-13\"]/div[2]/div/div[2]/section["+i+"]/ul/li"));
			assertEquals(towns.size(), claimed_amount);
		}
	}

	@AfterClass
	public static void exit(){
		driver.close();
	}

}
