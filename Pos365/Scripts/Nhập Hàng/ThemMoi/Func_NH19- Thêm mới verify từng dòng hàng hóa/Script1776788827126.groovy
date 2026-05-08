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

import com.pages.PurchasePage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
PurchasePage page = new PurchasePage()
def data = findTestData("Purchase/NhapNhieuHangHoa")

String suffix = new Date().format('HHmmss')
String maCTInput = maCT + suffix
String nhaCungCapInput = nhaCungCap
String ngayNhapInput = ngayNhap
String nguoiTao = "Admin"
boolean isHoanThanh = true
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhThemHang()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/txt_MaCT'), maCTInput)
page.searchNCCByMa(nhaCungCapInput)
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/date_NgayNhap'), ngayNhapInput)
List<Map> expectedItems = []

for (int i = 1; i <= data.getRowNumbers(); i++) {

	String maHang = data.getValue("MaHang", i)
	String soLuong = data.getValue("SoLuong", i)
	String giaNhap = data.getValue("GiaNhap", i)

	if (!maHang || maHang.trim().isEmpty()) continue
	WebUI.comment("Add: ${maHang} | SL=${soLuong} | Giá=${giaNhap}")
	page.themSanPhamDayDu(maHang, soLuong, giaNhap)
	expectedItems.add([
		maHang : maHang.trim(),
		soLuong: soLuong.trim(),
		giaNhap: giaNhap.trim()
	])
	WebUI.delay(0.5)
}

double tongThanhTien = page.verifyThanhTienTungDong()
page.verifyTongCong(tongThanhTien)
if (isHoanThanh) {
	page.clickDone()
} else {
	page.clickSave()
}
WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_DongYHoanThanh'))
page.moManHinhNhapHang()
page.searchProductByMa(maCTInput)
WebUI.delay(2)

String expectedStatus = isHoanThanh ? "Hoàn thành" : "Đang xử lý"

page.verifyThongTinChiTiet(maCTInput,nhaCungCapInput,ngayNhapInput,nguoiTao,tongThanhTien,expectedStatus)
page.verifyDanhSachHangHoa(expectedItems)
WebUI.comment(" PASS FULL CASE DATA-DRIVEN")