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
productPage.clickSaveAndCopy()
WebUI.delay(3)
Map dataForm = productPage.getValuesOnForm()
assert dataForm.ten.trim() == ten.trim()
assert productPage.formatData(dataForm.giaBan) == productPage.formatData(giaBan)
assert productPage.formatData(dataForm.giaVon) == productPage.formatData(giaVon)
assert productPage.formatData(dataForm.tonKho) == '0'

WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_MaHangHoa'), maSP2)
productPage.clickSave()
WebUI.delay(2)
productPage.searchProductByMa(maSP1)
WebUI.delay(1)
productPage.verifyProduct(ten, maSP1, giaBan, giaVon, tonKho, dvt)
productPage.searchProductByMa(maSP2)
WebUI.delay(1)
productPage.verifyProduct(ten, maSP2, giaBan, giaVon, "0", dvt)
WebUI.comment('KẾT QUẢ: Hoàn thành kiểm tra 2 sản phẩm!')