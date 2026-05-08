import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage


WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)
ProductPage productPage = new ProductPage()
String suffix = new Date().format('HHmmss')
productPage.navigateToCreate()
String ma_HH = "add"+suffix
String maSua = "Fix"+ suffix
String maMoi = "NEW" + suffix

productPage.createProduct(ma_HH,"Bánh ngọt", "10000","9000","10","cái")
productPage.clickSave()
WebUI.delay(2)
String tenSua = "Sữa Tươi Vinamilk 180ml (Đã Sửa)"
String giaBanSua = "15000"
String giaVonSua = "12000"
String tonKhoSua = "50"
String dvtSua = "Hộp"

String tenMoi = "Bánh Quy Oreo 133g"
String giaBanMoi = "25000"
String giaVonMoi = "18000"
String tonKhoMoi = "100"
String dvtMoi = "Gói"

productPage.navigateToList()
productPage.searchProductByMa(ma_HH)
productPage.clickFix()


productPage.createProduct(maSua, tenSua, giaBanSua, giaVonSua, tonKhoSua, dvtSua)
productPage.clickSaveAndAdd()
WebUI.delay(2)

Map dataFormSauKhiLuu = productPage.getValuesOnForm()

assert dataFormSauKhiLuu.ma.trim() == ""
assert dataFormSauKhiLuu.ten.trim() == ""
assert productPage.formatData(dataFormSauKhiLuu.giaBan) == "0"
assert productPage.formatData(dataFormSauKhiLuu.giaVon) == "0"

productPage.createProduct(maMoi, tenMoi, giaBanMoi, giaVonMoi, tonKhoMoi, dvtMoi)
productPage.clickSave()
WebUI.delay(2)
productPage.navigateToList()
productPage.searchProductByMa(maSua)
productPage.verifyProduct(tenSua, maSua, giaBanSua, giaVonSua, tonKhoSua, dvtSua)
WebUI.delay(2)
productPage.navigateToList()
WebUI.delay(2)
productPage.searchProductByMa(maMoi)
WebUI.delay(2)
productPage.verifyProduct(tenMoi, maMoi, giaBanMoi, giaVonMoi, tonKhoMoi, dvtMoi)

WebUI.comment('KẾT QUẢ: Thành công - Sản phẩm cũ đã cập nhật, form đã reset và thêm được sản phẩm mới.')