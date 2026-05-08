import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
PurchasePage page = new PurchasePage()
def data = findTestData("Purchase/NhapNhieuHangHoa")
String suffix = new Date().format('HHmmss')
String maCT = maCT + suffix
String nhaCungCap = nhaCungCap
String ngayNhap = ngayNhap
String nguoiTao = "Admin"
boolean isHoanThanh = false 
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)
page.searchNCCByMa(nhaCungCap)
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/date_NgayNhap'), ngayNhap)
for (int i = 1; i <= data.getRowNumbers(); i++) {

	String maHang = data.getValue("MaHang", i)
	String soLuong = data.getValue("SoLuong", i)
	String giaNhap = data.getValue("GiaNhap", i)

	if (!maHang || maHang.trim().isEmpty()) continue

	WebUI.comment("👉 Add: ${maHang} | SL=${soLuong} | Giá=${giaNhap}")

	page.themSanPhamDayDu(maHang, soLuong, giaNhap)

	WebUI.delay(0.5)
}
double tongThanhTien = page.verifyThanhTienTungDong()

assert tongThanhTien > 0 : "Không có dữ liệu sản phẩm"

page.verifyTongCong(tongThanhTien)

if (isHoanThanh) {
	WebUI.comment(" Click Hoàn thành")
	page.clickDone()
	WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_DongYHoanThanh'))
} else {
	WebUI.comment("Click Lưu")
	page.clickSave()
}
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
WebUI.delay(2)

String expectedStatus = isHoanThanh ? "Hoàn thành" : "Đang xử lý"

page.verifyThongTinChiTiet(maCT,nhaCungCap,ngayNhap,nguoiTao,tongThanhTien,expectedStatus)

WebUI.comment("pass tất cả các thông tin")