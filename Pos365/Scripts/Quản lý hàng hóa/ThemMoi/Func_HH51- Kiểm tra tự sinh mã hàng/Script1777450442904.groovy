import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage
import com.kms.katalon.core.model.FailureHandling as FailureHandling
WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)
ProductPage productPage = new ProductPage()

String suffix = new Date().format('HHmmss')
String Ten = Ten_HH +suffix
productPage.navigateToCreate()

productPage.createProductKhongMa(Ten,giaBan,giaVon,tonKho,DVT)

productPage.clickSave()

WebUI.delay(2)
productPage.searchProductByMa(Ten)
String ma = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/txt_MaHangHoaCT'))
WebUI.comment("Mã tự sinh là: " + ma)
WebUI.comment('sinh mã ngẫu nhiên thành công')
WebUI.takeScreenshot()

