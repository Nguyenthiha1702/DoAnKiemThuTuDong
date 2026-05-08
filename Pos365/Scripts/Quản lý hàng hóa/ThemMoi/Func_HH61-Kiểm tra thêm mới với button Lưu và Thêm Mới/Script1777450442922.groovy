import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.pages.ProductPage as ProductPage


WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)

ProductPage productPage = new ProductPage()
String suffix = new Date().format('HHmmss')
String maSP1 = ma1 + suffix
String maSP2 = ma2 + suffix
productPage.navigateToCreate()
productPage.createProduct(maSP1, ten, giaBan, giaVon, tonKho, dvt)
productPage.clickSaveAndAdd()
WebUI.delay(2)

Map dataForm = productPage.getValuesOnForm()

assert dataForm.ma.trim() == ""
assert dataForm.ten.trim() == ""
assert productPage.formatData(dataForm.giaBan) == "0"
assert productPage.formatData(dataForm.giaVon) == "0"
assert productPage.formatData(dataForm.tonKho) == "0"

productPage.createProduct(maSP2, ten, giaBan, giaVon, tonKho, dvt)
productPage.clickSave()
WebUI.delay(2)
productPage.navigateToList()
productPage.searchProductByMa(maSP1)
WebUI.delay(1)
productPage.verifyProduct(ten, maSP1, giaBan, giaVon, tonKho, dvt)
productPage.searchProductByMa(maSP2)
WebUI.delay(1)
productPage.verifyProduct(ten, maSP2, giaBan, giaVon, tonKho, dvt)
WebUI.comment('KẾT QUẢ: Pass - SP1 và SP2 đều lưu đúng, Form Add New đã reset chuẩn.')