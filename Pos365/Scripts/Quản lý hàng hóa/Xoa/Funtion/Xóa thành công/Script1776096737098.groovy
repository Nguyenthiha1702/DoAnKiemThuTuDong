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

import com.pages.ProductPage
WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)
ProductPage productPage = new ProductPage()
productPage.navigateToList()
println "Đang tìm kiếm mã: " + ma
productPage.searchProductByMa(ma)
productPage.clickDelete()
productPage.clickYes()
boolean isVisible = WebUI.waitForElementVisible(findTestObject('Hàng hóa/Xoa/tb_Xoa'), 5, FailureHandling.OPTIONAL)
productPage.navigateToList()
productPage.searchProductByMa(ma)
boolean isNotExist = WebUI.verifyElementNotPresent(findTestObject('Hàng hóa/XemChiTiet/txt_MaHangHoaCT'), 5)

if (isNotExist) {
	WebUI.comment("PASS: Sản phẩm mã " + ma + " đã được xóa và không còn tìm thấy.")
} else {
	WebUI.takeScreenshot()
	WebUI.verifyElementNotPresent(findTestObject('Hàng hóa/XemChiTiet/txt_MaHangHoaCT'), 1, FailureHandling.STOP_ON_FAILURE)
}