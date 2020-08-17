package pages;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Pageawardlist {
    public WebDriver driver;
Logger log= Logger.getLogger(Pageawardlist.class.getName());
    public static String countrylist = "{$countrylist}";
    private static String countryselected = String.format("//*[@id=\"sideNav\"]/ul/li[%s]/a", countrylist);

    public static String button = "{$button}";
    private static String menubutton = String.format("//li/a[text()='%s']", button);
    public Pageawardlist() throws IOException {
    }

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        Pageawardlist al = PageFactory.initElements(driver, Pageawardlist.class);
        driver.get("https://www.dbs.com.sg/index/default.page");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void learnmorebtn() {

        WebElement learnmorebtn = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));

        System.out.println(learnmorebtn.getText());
        learnmorebtn.click();
    }

    public void selectcountry() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",selectedcountry);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        WebElement selectedcountry = driver.findElement(By.xpath("//*[@id=\"sideNav\"]/ul/li[3]/a"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",selectedcountry);

        Actions a= new Actions(driver);
       a.moveToElement(selectedcountry).click().perform();

    }
    //*[@id="bodywrapper"]/div[2]/section/div[1]/div[2]/div/div[5]/table
    public void clickmenu()
    {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement buttontobeclicked= driver.findElement(By.xpath("//*[@id=\"flpHeader\"]/header/div/div[2]/ul/li[1]"));
     Actions a= new Actions(driver);
        //*[@id="flpHeader"]/header/div/div[2]/ul/li[1]
     a.moveToElement(buttontobeclicked).click().perform();
     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }
    public void clicksubmenu()
    {


        FluentWait f= new FluentWait(driver);

        f.withTimeout(30,TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS);
        //WebElement submenutobeclicked= driver.findElement(By.xpath(String.format(menubutton.replace(button,submenu))));
        WebElement submenutobeclicked= driver.findElement(By.xpath("//*[@id=\"bodywrapper\"]/div[1]/div[1]//li[2]/a"));
        Actions a= new Actions(driver);
        a.moveToElement(submenutobeclicked).click().perform();
    }

    public void awards()
    {

        WebElement ourawards= driver.findElement(By.xpath("//a[text()='Our Awards & Accolades']"));
        ourawards.click();
        List<WebElement> awardsreceived= driver.findElements(By.xpath("//div[@class='row mBot-20']"));
        awardsreceived.size();
        Assert.assertEquals(22,awardsreceived.size());
        WebElement award1=driver.findElement(By.xpath("//div[3]//h2"));
       Assert.assertEquals("A World First",award1.getText());
       WebElement subaward1= driver.findElement(By.xpath("//div[2]/h3"));
       Assert.assertEquals("Euromoney",subaward1.getText());



    }


    public void writetabletoExcel() throws IOException {

        WebElement table = driver.findElement(By.xpath("//*[@id=\"bodywrapper\"]//table"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int rowcount = rows.size();
        FileOutputStream fis = new FileOutputStream(new File(".\\src\\test\\resources\\data\\Testing.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet("DBS");
        for (int row = 0; row < rowcount; row++) {
            List<WebElement> columns_inrow = rows.get(row).findElements(By.tagName("td"));
            int columns_count = columns_inrow.size();
            System.out.println("Number of cells in Row " + row + " are " + columns_count);
            Row ro = sh.createRow(row);
            for (int column = 0; column < columns_count; column++) {

                String celltext = columns_inrow.get(column).getText();
                System.out.println(
                        "Cell Value of row number " + row + " and column number " + column + " Is " + celltext);
                ro.createCell(column).setCellValue(celltext);
            }
            System.out.println("===========================");

        }
        wb.write(fis);
        wb.close();

    }





    public void ReadExcel() throws IOException{
        String Return = null;
        try {
            File file1 = new File(".\\src\\test\\resources\\data\\Testing.xlsx");
            FileInputStream fis1 = new FileInputStream(file1);

            XSSFWorkbook workbook1 = new XSSFWorkbook(file1);

            XSSFSheet sheet = workbook1.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row1 = rowIterator.next();

                Iterator<Cell> cellIterator = row1.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cell.getStringCellValue();
                    log.info("Cell value"+cell.getStringCellValue());

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


        }

public void clearexcel() {
    try {
        File file1 = new File(".\\src\\test\\resources\\data\\Testing.xlsx");
        FileInputStream fis1 = new FileInputStream(file1);
        XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);

        Iterator<Row> rowIterator1 = sheet1.iterator();

        while (rowIterator1.hasNext()) {
            rowIterator1.next();
            rowIterator1.remove();

        }
        log.info("Sheet cleared");
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (InvalidFormatException e) {
        e.printStackTrace();
    }
}


        public void reporttable() throws IOException {

            File file = new File("C:\\Users\\hp\\IdeaProjects\\DBS_Final\\target\\cucumber-reports\\cucumber-pretty\\index.html");
            FileReader fw = null;
            if (file.exists()) {
                BufferedReader br = new BufferedReader(fw);
                String line = br.readLine();
                if (file != null) {
                    br.readLine();
                } else {
                    PrintWriter pw = new PrintWriter("C:\\Users\\hp\\IdeaProjects\\DBS_Final\\target\\cucumber-reports\\cucumber-pretty\\index.html");
                    pw.println("<TABLE BORDER><TR><TH>Award Name<TH>Caption of the award</TR>" + "\n");


                    pw.println("<TABLE BORDER><TR>A World First</TR>" +
                            "<TR>blah</TR>");


                    pw.println("</TABLE>");
                    pw.close();
                }
            }
       /*PrintWriter pw = new PrintWriter("C:\\Users\\hp\\IdeaProjects\\DBS_Final\\target\\cucumber-reports\\cucumber-pretty\\index.html");
            pw.println("<TABLE BORDER><TR><TH>Award Name<TH>Caption of the award</TR>"+"\n");


                pw.println("<TABLE BORDER><TR>A World First</TR>"+
                "<TR>blah</TR>");


            pw.println("</TABLE>");
            pw.close();
            PrintWriter pw1= new PrintWriter("C:\\Users\\hp\\IdeaProjects\\DBS_Final\\target\\cucumber-reports\\cucumber-pretty\\index2.html");
            BufferedReader br1=new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\DBS_Final\\target\\cucumber-reports\\cucumber-pretty\\index.html"));
            BufferedReader br2=new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\DBS_Final\\target\\cucumber-reports\\cucumber-pretty\\index1.html"));
      String line1= br1.readLine();
      String line2= br2.readLine();

      while(line1!=null || line2!=null)
      {
          if(line1!=null)
          {
              pw1.println(line1);
              line1= br1.readLine();
          }
          if(line2!=null)
          {
              pw1.println(line2);
              line2= br2.readLine();
          }
      }
      pw1.flush();
      br1.close();
      br2.close();
      pw1.close();

        }*/

        }

    }








