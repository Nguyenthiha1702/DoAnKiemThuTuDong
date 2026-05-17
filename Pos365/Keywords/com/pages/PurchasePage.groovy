package com.pages
import org.openqa.selenium.By
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

import internal.GlobalVariable

public class PurchasePage {

	def moManHinhNhapHang() {
		def menuGiaoDich = findTestObject('Bán Hàng/menu_GiaoDich')
		def subNhapHang  = findTestObject('Nhập Hàng/sub_NhapHang')

		WebUI.waitForElementVisible(menuGiaoDich, 5)
		WebUI.scrollToElement(menuGiaoDich, 5)
		WebUI.waitForElementClickable(menuGiaoDich, 5)
		WebUI.click(menuGiaoDich)
		WebUI.delay(2)

		WebUI.waitForElementVisible(subNhapHang, 5)
		WebUI.scrollToElement(subNhapHang, 5)
		WebUI.waitForElementClickable(subNhapHang, 5)
		WebUI.click(subNhapHang)

		WebUI.waitForPageLoad(5)
	}
	def moManHinhThemHang() {
		WebUI.click(findTestObject('Bán Hàng/menu_GiaoDich'))
		WebUI.click(findTestObject('Nhập Hàng/sub_NhapHang'))
		WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_ThemMoi'))
		WebUI.delay(2)
	}

	def searchProductByMa(String ma) {
		def obj = findTestObject('Nhập hàng/TimKiem/txt_TimTheoMaCT')

		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)

		WebUI.click(obj)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}
	def searchProductByMaHang(String ma) {
		def obj = findTestObject('Nhập hàng/TimKiem/txt_TimTheoMaHH')

		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)
		WebUI.click(obj)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}

	def searchProductByMaDT(String ma) {
		def obj = findTestObject('Nhập hàng/TimKiem/txt_TimDoiTac')

		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)
		WebUI.click(obj)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}


	def themSanPhamDayDu(String tenHang, String soLuong, String donGia) {

		TestObject txtSearch = findTestObject('Nhập Hàng/ThemMoi/txt_TimKiem')
		WebUI.setText(txtSearch, tenHang)
		WebUI.delay(1)
		WebUI.sendKeys(txtSearch, Keys.chord(Keys.ENTER))
		TestObject objSL = new TestObject()
		objSL.addProperty("xpath", ConditionType.EQUALS,"(//input[@ng-model='dataItem.Quantity'])[1]")

		WebUI.waitForElementVisible(objSL, 10)
		WebUI.click(objSL)
		WebUI.clearText(objSL)
		WebUI.setText(objSL, soLuong)
		WebUI.sendKeys(objSL, Keys.chord(Keys.ENTER))
		TestObject objDG = new TestObject()
		objDG.addProperty("xpath", ConditionType.EQUALS,"(//tr[@role='row']//td[@data-field='Price']//input)[1]")

		WebUI.waitForElementVisible(objDG, 10)
		WebUI.click(objDG)
		WebUI.clearText(objDG)
		WebUI.setText(objDG, donGia)
		WebUI.sendKeys(objDG, Keys.chord(Keys.ENTER))

		WebUI.comment("Đã thêm: ${tenHang} | SL: ${soLuong} | Giá: ${donGia}")
	}

	def searchNCCByMa(String ma) {
		def obj = findTestObject('Nhập Hàng/ThemMoi/txt_TimKiemNCC')

		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)

		WebUI.click(obj)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.delay(2)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}

	def nhapChietKhau(String tienChietKhau) {
		WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/num_ChietKhau'), tienChietKhau)
		WebUI.sendKeys(findTestObject('Nhập Hàng/ThemMoi/num_ChietKhau'), Keys.chord(Keys.ENTER))
		WebUI.delay(2)
	}

	def double verifyThanhTienTungDong() {

		List<WebElement> rows = WebUI.findWebElements(findTestObject('Nhập Hàng/ThemMoi/xpath_AllProductRows'), 10)
		double tong = 0
		for (int i = 0; i < rows.size(); i++) {
			WebElement row = rows.get(i)
			double soLuong = parseMoney(row.findElement(By.xpath(".//input[@ng-model='dataItem.Quantity']")).getAttribute("value"))
			double donGia = parseMoney(row.findElement(By.xpath(".//td[@data-field='Price']//input")).getAttribute("value"))
			double thanhTienUI = parseMoney(row.findElement(By.xpath(".//td[@data-field='Price']//span")).getText()	)
			double expected = soLuong * donGia
			WebUI.comment("Row ${i+1}: SL=${soLuong} | DG=${donGia} | Expected=${expected} | UI=${thanhTienUI}")
			assert Math.abs(expected - thanhTienUI) < 1 : "❌ Sai dòng ${i+1}"
			tong += thanhTienUI
		}

		return tong
	}

	def verifyTongCong(double tongTinhToan) {

		double tongUI = parseMoney(WebUI.getText(findTestObject('Nhập Hàng/ThemMoi/num_TongCong')))
		WebUI.comment("Tổng tính: " + tongTinhToan)
		WebUI.comment("Tổng UI: " + tongUI)

		if (Math.abs(tongTinhToan - tongUI) > 1) {
			WebUI.takeScreenshot("Loi_TongCong")
			assert false : "Sai tổng cộng"
		} else {
			WebUI.comment("Tổng đúng")
		}
	}



	def String getMaCT() {
		TestObject obj = findTestObject('Nhập Hàng/XemChiTiet/txt_maCT')
		WebUI.waitForElementVisible(obj, 5)
		String maCT = WebUI.getAttribute(obj, "value")?.trim()
		WebUI.comment("Mã CT: " + maCT)
		return maCT
	}


	def clickSave() {
		WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_Luu'))
		WebUI.waitForPageLoad(2)
	}
	def clickDone() {
		WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_HoanThanh'))
		WebUI.waitForPageLoad(2)
	}
	def clickExit() {
		WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_Thoat'))
		WebUI.waitForPageLoad(2)
	}

	def clickCapNhat() {
		WebUI.click(findTestObject('Nhập Hàng/CapNhat/but_CapNhat'))
		WebUI.waitForPageLoad(2)
	}

	def clickCopy() {
		WebUI.click(findTestObject('Nhập Hàng/Copy/but_Copy'))
		WebUI.waitForPageLoad(2)
	}

	def clickHuy() {
		WebUI.click(findTestObject('Nhập Hàng/Huy/but_Huy'))
		WebUI.waitForPageLoad(2)
	}

	def clickThoatHuy() {
		WebUI.click(findTestObject('Nhập hàng/Huy/but_Thoat'))
		WebUI.waitForPageLoad(2)
	}

	def clickDongYHuy() {
		WebUI.click(findTestObject('Nhập hàng/Huy/but_DongYHuy'))
		WebUI.waitForPageLoad(2)
	}
	def clickXHuy() {
		WebUI.click(findTestObject('Nhập hàng/Huy/but_X'))
		WebUI.waitForPageLoad(2)
	}


	def checkErrDuLieu() {
		boolean isVisible = WebUI.waitForElementVisible(findTestObject('Nhập hàng/ThemMoi/err_KhongDuThongTin'), 5, FailureHandling.OPTIONAL)
		if (isVisible) {
			return true
		} else {
			return false
		}
	}
	def checkErrMaCT() {
		boolean isVisible = WebUI.waitForElementVisible(findTestObject('Nhập hàng/ThemMoi/err_TrungMaCT'), 5, FailureHandling.OPTIONAL)
		if (isVisible) {
			return true
		} else {
			return false
		}
	}
	def getDanhSachHangHoa() {
		List<WebElement> rows = WebUI.findWebElements(findTestObject('Nhập Hàng/ThemMoi/xpath_AllProductRows'), 10)

		def list = []

		for (int i = 0; i < rows.size(); i++) {
			WebElement row = rows.get(i)

			String raw = row.findElement(By.xpath(".//td[@data-field='Code']")).getText()?.toString().trim()
			String maHang = raw.split("\\s")[0]
			String soLuong = row.findElement(By.xpath(".//input[@ng-model='dataItem.Quantity']")).getAttribute("value")
			String giaNhap = row.findElement(By.xpath(".//td[@data-field='Price']//input")).getAttribute("value")

			list.add([
				maHang : maHang,
				soLuong: soLuong,
				giaNhap: giaNhap
			])
		}

		return list
	}
	def getThongTinChiTiet() {

		TestObject maCTObj      = findTestObject('Nhập Hàng/XemChiTiet/txt_maCT')
		TestObject nccObj       = findTestObject('Nhập Hàng/XemChiTiet/txt_NhaCungCap')
		TestObject ngayObj      = findTestObject('Nhập Hàng/XemChiTiet/date_NgayNhap')
		TestObject nguoiTaoObj  = findTestObject('Nhập Hàng/XemChiTiet/txt_NguoiTao')
		TestObject trangThaiObj = findTestObject('Nhập Hàng/XemChiTiet/txt_TrangThai')
		TestObject tongObj      = findTestObject('Nhập Hàng/XemChiTiet/num_TongCong')

		WebUI.waitForElementVisible(maCTObj, 10)

		return [
			maCT      : WebUI.getText(maCTObj)?.trim(),
			ncc       : WebUI.getText(nccObj)?.trim(),
			ngay      : WebUI.getText(ngayObj)?.trim(),
			nguoiTao  : WebUI.getText(nguoiTaoObj)?.trim(),
			trangThai : WebUI.getText(trangThaiObj)?.trim(),
			tong      : parseMoney(WebUI.getText(tongObj)?.trim())
		]
	}

	def verifyThongTinChiTiet(String expectedMaCT,String expectedNCC,String expectedNgayNhap,String expectedNguoiTao,double expectedTongCong,String expectedTrangThai) {

		def data = getThongTinChiTiet()

		assert data.maCT != null && !data.maCT.isEmpty() :"Không lấy được mã CT từ UI"
		assert data.maCT == expectedMaCT :"Sai mã CT | expected=${expectedMaCT} | actual=${data.maCT}"
		assert data.ncc.contains(expectedNCC) :"Sai NCC | expected=${expectedNCC} | actual=${data.ncc}"
		assert data.ngay.contains(expectedNgayNhap) :"Sai ngày nhập | expected=${expectedNgayNhap} | actual=${data.ngay}"
		assert data.nguoiTao.contains(expectedNguoiTao) :"Sai người tạo | expected=${expectedNguoiTao} | actual=${data.nguoiTao}"
		assert data.trangThai.contains(expectedTrangThai) :"Sai trạng thái | expected=${expectedTrangThai} | actual=${data.trangThai}"
		assert Math.abs(data.tong - expectedTongCong) < 1 :"Sai tổng cộng | expected=${expectedTongCong} | actual=${data.tong}"
	}
	
	def verifyMotDong(String maHang, String soLuongExp, String giaNhapExp) {

		TestObject objMa  = findTestObject('Nhập Hàng/XemChiTiet/row_maHang', ['maHang': maHang])
		TestObject objSL  = findTestObject('Nhập Hàng/XemChiTiet/row_SoLuong', ['maHang': maHang])
		TestObject objGia = findTestObject('Nhập Hàng/XemChiTiet/row_GiaNhap', ['maHang': maHang])

		WebUI.waitForElementVisible(objMa, 10)

		String maUI  = WebUI.getText(objMa).trim()
		String slUI  = WebUI.getText(objSL).trim()
		String giaUI = WebUI.getText(objGia).trim()

		WebUI.comment("=== VERIFY ITEM ===")
		WebUI.comment("Mã: " + maUI)
		WebUI.comment("SL: " + slUI + " | Exp: " + soLuongExp)
		WebUI.comment("Giá: " + giaUI + " | Exp: " + giaNhapExp)

		assert maUI == maHang :"Sai mã hàng"
		assert parseMoney(slUI) == parseMoney(soLuongExp) :"Sai số lượng | expected=" + soLuongExp + " | actual=" + slUI
		assert parseMoney(giaUI) == parseMoney(giaNhapExp) :"Sai giá nhập | expected=" + giaNhapExp + " | actual=" + giaUI
	}



	def verifyDanhSachHangHoa(def items) {
		assert items != null && items.size() > 0 :"Không có dữ liệu để verify"
		for (def item : items) {
			verifyMotDong(item.maHang,item.soLuong,item.giaNhap)
		}
		WebUI.comment("PASS VERIFY DANH SÁCH HÀNG")
	}

	String getFirstProductCode() {
		TestObject to = findTestObject('Nhập hàng/XemChiTiet/txt_MaHangDauTien')
		WebUI.waitForElementVisible(to, 10)
		return WebUI.getText(to)?.trim()
	}

	def verifyFirstProductCodeNo(String expected) {
		def obj = findTestObject('Nhập Hàng/XemChiTiet/txt_MaHangDauTien')

		boolean isPresent = WebUI.verifyElementPresent(obj, 3, FailureHandling.OPTIONAL)

		assert !isPresent :
		"Không mong đợi tìm thấy mã '${expected}' nhưng vẫn xuất hiện"
	}

	def verifyFirstProductCode(String expected) {
		def obj = findTestObject('Nhập Hàng/XemChiTiet/txt_MaHangDauTien')
		WebUI.waitForElementVisible(obj, 5, FailureHandling.STOP_ON_FAILURE)

		String actual = WebUI.getText(obj).trim()

		assert actual == expected :
		"Sai mã hàng | expected='${expected}' | actual='${actual}'"
	}


	def verifyMain(String maCT, String nguoiTao, double tong) {
		WebUI.waitForElementVisible(findTestObject('Nhập Hàng/XemChiTiet/txt_maCT'), 10)

		String ma = WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/txt_maCT'))?.trim()
		String nt = WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/txt_NguoiTao'))?.trim()
		double t  = parseMoney(WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/num_TongCong'))?.trim())

		assert ma && ma == maCT
		assert nt.contains(nguoiTao)
		assert Math.abs(t - tong) < 1
	}
	def verifyNCC(String ncc) {
		String v = WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/txt_NhaCungCap'))?.trim()
		assert v.contains(ncc)
	}

	def verifyNgay(String ngay) {
		String v = WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/date_NgayNhap'))?.trim()
		assert v.contains(ngay)
	}
	def verifyTrangThai(String tt) {
		String v = WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/txt_TrangThai'))?.trim()
		assert v.contains(tt)
	}
	def verifyNguoiTao(String nguoiTao) {
		String v = WebUI.getText(findTestObject('Nhập Hàng/XemChiTiet/txt_NguoiTao'))?.trim()
		assert v.contains(nguoiTao)
	}

	def validateTextBox(String testObjectPath,String expectedValue,boolean shouldMatch) {
		try {
			String actual = WebUI.getAttribute(findTestObject(testObjectPath),'value')
			boolean actualResult = actual.trim() == expectedValue.trim()
			assert actualResult == shouldMatch
		}
		catch(Exception e){
			WebUI.takeScreenshot()
			throw e
		}
	}
	def validateNumber(path, expectedValue, shouldMatch){

		String actual = WebUI.getAttribute(findTestObject(path), 'value')
		boolean actualResult
		if(isNumeric(actual) && isNumeric(expectedValue)){
			actualResult =new BigDecimal(formatData(actual)).compareTo(new BigDecimal(formatData(expectedValue))) == 0
		}
		else{
			actualResult = formatData(actual) == formatData(expectedValue)
		}
		assert actualResult == shouldMatch
	}
	
	def formatData(String value) {
		if (value == null) return ""
		String cleanValue = value.replaceAll(",", "")
		if (cleanValue.endsWith(".0")) {
			cleanValue = cleanValue.substring(0, cleanValue.length() - 2)
		}
		return cleanValue.trim()
	}


	def isNumeric(String v){
		if(v == null) return false

		v = v.replaceAll(",", "")

		return v ==~ /^\d+(\.\d+)?$/
	}

	def double parseMoney(String text) {
		if (!text) return 0
		String clean = text.replaceAll("[^\\d]", "")
		try {
			return Double.parseDouble(clean)
		} catch (Exception e) {
			return 0
		}
	}
}
