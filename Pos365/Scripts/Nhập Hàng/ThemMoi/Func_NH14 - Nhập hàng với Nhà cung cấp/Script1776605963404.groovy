
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.pages.PurchasePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys

PurchasePage page = new PurchasePage()
def data = findTestData("Purchase/NhapNhieuHangHoa")
String suffix = new Date().format('HHmmss')
String maCT = maCT + suffix
String nhaCungCap = nhaCungCap
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)
page.searchNCCByMa(nhaCungCap)
String tenHang = "SP001"
String soLuong = "2"
String giaBan = "10000"
page.themSanPhamDayDu(tenHang, soLuong,giaBan)
page.clickSave()
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
WebUI.delay(2)
page.verifyNCC(nhaCungCap)

