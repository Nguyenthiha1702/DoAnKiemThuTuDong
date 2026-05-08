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

import  com.pages.ProductPage
WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)

ProductPage productPage = new ProductPage()

productPage.navigateToCreate()
String suffix = new Date().format('HHmmss')
String maSP1 = ma + suffix
productPage.createProduct(maSP1, ten, giaBan, giaVon, tonKho, dvt)
productPage.clickExit()
productPage.searchProductByMa(maSP1)
WebUI.delay(2)
WebUI.verifyElementNotPresent(findTestObject('Hàng hóa/TimKiem/Txt_MaTrongDanhSach', [('ma') : maSP1]), 5)
println "Xác nhận: Sản phẩm mã " + ma + " không tồn tại trong hệ thống. Test Case Lưu và Thoát thành công!"
