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

// ===== IMPORT =====
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.PurchasePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys

// ===== INIT =====
PurchasePage page = new PurchasePage()
def data = findTestData("Purchase/NhapNhieuHangHoa")
String suffix = new Date().format('HHmmss')
// ===== VARIABLE =====
String maCT = maCT + suffix
String nhaCungCap = nhaCungCap
String ngayNhap = ngayNhap
String nguoiTao = "Admin"
boolean isHoanThanh = true  // true = hoàn thành

// ===== LOGIN =====
WebUI.callTestCase(findTestCase("common/login"), [:])

// ===== OPEN =====
page.moManHinhThemHang()

// ===== NHẬP THÔNG TIN =====
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCT)

page.searchNCCByMa(nhaCungCap)

WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/date_NgayNhap'), ngayNhap)

// ===== ADD MULTI PRODUCT =====
for (int i = 1; i <= data.getRowNumbers(); i++) {

	String maHang = data.getValue("MaHang", i)
	String soLuong = data.getValue("SoLuong", i)
	String giaNhap = data.getValue("GiaNhap", i)

	if (!maHang || maHang.trim().isEmpty()) continue

	WebUI.comment("👉 Add: ${maHang} | SL=${soLuong} | Giá=${giaNhap}")

	page.themSanPhamDayDu(maHang, soLuong, giaNhap)

	WebUI.delay(0.5)
}

// ===== VERIFY TỪNG DÒNG =====
double tongThanhTien = page.verifyThanhTienTungDong()

assert tongThanhTien > 0 : "❌ Không có dữ liệu sản phẩm"

// ===== VERIFY TỔNG =====
page.verifyTongCong(tongThanhTien)

// ===== CLICK LƯU / HOÀN THÀNH =====
if (isHoanThanh) {
	WebUI.comment("👉 Click Hoàn thành")
	page.clickDone()
} else {
	WebUI.comment("👉 Click Lưu")
	page.clickSave()
}
WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_DongYHoanThanh'))


// ===== BACK VỀ DANH SÁCH =====

// ===== TÌM THEO MÃ CT =====
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
WebUI.delay(2)

// ===== XÁC ĐỊNH TRẠNG THÁI =====
String expectedStatus = isHoanThanh ? "Hoàn thành" : "Đang xử lý"

// ===== VERIFY CHI TIẾT =====
page.verifyThongTinChiTiet(
	maCT,
	nhaCungCap,
	ngayNhap,
	nguoiTao,
	tongThanhTien,
	expectedStatus
)

// ===== DONE =====
WebUI.comment("✅ PASS - CASE 4 (MULTI PRODUCT)")