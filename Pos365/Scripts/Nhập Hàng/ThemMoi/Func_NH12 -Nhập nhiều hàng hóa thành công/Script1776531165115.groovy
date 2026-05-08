import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage

PurchasePage page = new PurchasePage()
def data = findTestData("Purchase/NhapNhieuHangHoa")

WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
for (int i = 1; i <= data.getRowNumbers(); i++) {

	String maHang = data.getValue("MaHang", i)
	String soLuong = data.getValue("SoLuong", i)
	String giaNhap = data.getValue("GiaNhap", i)

	if (!maHang || maHang.trim().isEmpty()) {
		continue
	}

	WebUI.comment("Thêm dòng " + i + ": " + maHang)

	page.themSanPhamDayDu(maHang, soLuong, giaNhap)
	WebUI.delay(1)
}

double tongTinh = page.verifyThanhTienTungDong()
assert tongTinh > 0 : "Tổng tính = 0 → có lỗi lấy dữ liệu"
page.verifyTongCong(tongTinh)

WebUI.comment("PASS - Nhập nhiều hàng thành công")