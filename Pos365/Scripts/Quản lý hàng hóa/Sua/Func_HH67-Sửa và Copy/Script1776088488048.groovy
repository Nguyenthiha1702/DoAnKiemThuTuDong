import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage


WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)
ProductPage productPage = new ProductPage()

String ten = "Sữa Tươi Vinamilk 180ml"
String giaBan = "15000"
String giaVon= "12000"
String tonKho= "50"
String dvt= "Hộp"

String suffix = new Date().format('HHmmss')
String maSP1 = ma1 + suffix
String maSP2 = ma2 + suffix
String ma_HH = "add"+suffix
productPage.navigateToCreate()

productPage.createProduct(ma_HH,"Bánh ngọt", "10000","9000","10","cái")
productPage.clickSave()
WebUI.delay(2)
productPage.navigateToList()
productPage.searchProductByMa(ma_HH)
productPage.clickFix()
productPage.createProduct(maSP1, ten, giaBan, giaVon, tonKho, dvt)
productPage.clickSaveAndCopy()
WebUI.delay(2)

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