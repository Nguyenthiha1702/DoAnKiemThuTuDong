import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject // CẦN THÊM DÒNG NÀY

import com.pages.PurchasePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

PurchasePage page = new PurchasePage()
def data = findTestData("Purchase/NhapNhieuHangHoa")

WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
for (int i = 1; i <= data.getRowNumbers(); i++) {
	String maHang = data.getValue("MaHang", i)
	String soLuong = data.getValue("SoLuong", i)
	String giaNhap = data.getValue("GiaNhap", i)

	if (maHang == null || maHang.isEmpty()) continue
	page.themSanPhamDayDu(maHang, soLuong, giaNhap)
}
double tongThanhTien = page.verifyThanhTienTungDong()

page.verifyTongCong(tongThanhTien)
page.nhapChietKhau(chietKhau)
WebUI.delay(1)
double ckValue = page.parseMoney(chietKhau)
double expectedThanhToan = tongThanhTien - ckValue
if (expectedThanhToan < 0) expectedThanhToan = 0
double tongThanhToanUI = page.parseMoney(WebUI.getText(findTestObject('Nhập Hàng/ThemMoi/num_TongCong')))
WebUI.comment("Tổng sau tính toán: ${tongThanhTien} - ${ckValue} = ${expectedThanhToan}")
WebUI.comment("Thực tế trên UI: ${tongThanhToanUI}")
assert Math.abs(expectedThanhToan - tongThanhToanUI) < 1 : " Lỗi: Tổng thanh toán cuối cùng không khớp!"
WebUI.comment(" PASS - Toàn bộ quy trình nhập hàng và tính toán chiết khấu chính xác")