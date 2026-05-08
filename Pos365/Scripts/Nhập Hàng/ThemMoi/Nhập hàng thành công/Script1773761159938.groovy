import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement

// ======================
// 1. LOGIN + MỞ MÀN HÌNH
// ======================
WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Bán Hàng/menu_GiaoDich'))
WebUI.click(findTestObject('Nhập Hàng/sub_NhapHang'))
WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_ThemMoi'))

WebUI.delay(2)


// ======================
// 2. NHẬP DATA
// ======================
def data = findTestData("TestHangHoa")

for (int i = 1; i <= data.getRowNumbers(); i++) {

	String tenHang = data.getValue("TenHang", i)
	String soLuong = data.getValue("SoLuong", i)

	// nhập tên hàng
	WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_TimKiem'), tenHang)
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Nhập Hàng/ThemMoi/txt_TimKiem'), Keys.chord(Keys.ENTER))
	WebUI.delay(2)

	// ===== FIX: luôn lấy dòng đầu =====
	TestObject objSL = new TestObject()
	objSL.addProperty("xpath", ConditionType.EQUALS,
		"(//input[@ng-model='dataItem.Quantity'])[1]")

	WebUI.waitForElementVisible(objSL, 10)
	WebUI.setText(objSL, soLuong)

	println(">>> Đã thêm: $tenHang - SL: $soLuong")
}


// ======================
// 3. VERIFY TỪNG DÒNG
// ======================
WebUI.delay(3)

List<WebElement> listRows = WebUI.findWebElements(
	findTestObject('Nhập Hàng/ThemMoi/xpath_AllProductRows'), 10)

println("Tổng số dòng thực tế: " + listRows.size())

double tongTinhToan = 0
boolean coLoiDong = false

for (int i = 0; i < listRows.size(); i++) {

	int row = i + 1

	// ===== XPath chuẩn theo [i] =====
	String xpathDG = "(//tr[@role='row']//td[@data-field='Price']//input)[" + row + "]"
	String xpathSL = "(//tr[@role='row']//input[@ng-model='dataItem.Quantity'])[" + row + "]"
	String xpathTT = "(//tr[@role='row']//td[@data-field='TotalAmount']//span)[" + row + "]"

	// ===== ĐƠN GIÁ =====
	TestObject objDG = new TestObject()
	objDG.addProperty("xpath", ConditionType.EQUALS, xpathDG)

	String strDonGia = WebUI.getAttribute(objDG, "value")

	// ===== SỐ LƯỢNG =====
	TestObject objSL = new TestObject()
	objSL.addProperty("xpath", ConditionType.EQUALS, xpathSL)

	String strSoLuong = WebUI.getAttribute(objSL, "value")

	// ===== THÀNH TIỀN =====
	TestObject objTT = new TestObject()
	objTT.addProperty("xpath", ConditionType.EQUALS, xpathTT)

	WebUI.waitForElementVisible(objTT, 5)
	String strThanhTien = WebUI.getText(objTT)

	// ===== TÍNH =====
	double donGia = parseMoney(strDonGia)
	double soLuong = parseMoney(strSoLuong)
	double thanhTienUI = parseMoney(strThanhTien)

	double thanhTienTinh = donGia * soLuong

	println("Dòng $row: Đơn giá=$donGia | SL=$soLuong | Thành tiền UI=$thanhTienUI")

	if (Math.abs(thanhTienTinh - thanhTienUI) > 1) {
		coLoiDong = true
		WebUI.comment("LỖI TẠI DÒNG $row: Tính $thanhTienTinh # UI $thanhTienUI")
	}

	tongTinhToan += thanhTienTinh
}


// ======================
// 4. VERIFY TỔNG
// ======================
String textTongCong = WebUI.getText(findTestObject('Nhập Hàng/ThemMoi/num_TongCong'))
double tongUI = parseMoney(textTongCong)

println("--------------------------------------")
println("TỔNG TÍNH TOÁN: " + tongTinhToan)
println("TỔNG TRÊN UI : " + tongUI)
println("--------------------------------------")

if (Math.abs(tongTinhToan - tongUI) > 1 || coLoiDong) {
	WebUI.comment("KẾT QUẢ: THẤT BẠI")
	assert false : "Sai lệch dữ liệu"
} else {
	println("KẾT QUẢ: THÀNH CÔNG")
}


// ======================
// HÀM XỬ LÝ TIỀN
// ======================
double parseMoney(String text) {
	if (text == null || text.isEmpty()) return 0
	String cleanText = text.replaceAll("[^\\d]", "")
	try {
		return Double.parseDouble(cleanText)
	} catch (Exception e) {
		return 0
	}
}