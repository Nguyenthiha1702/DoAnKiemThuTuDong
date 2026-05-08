package com.pages
import com.kms.katalon.core.model.FailureHandling
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class SellPage {

	def moManHinhBanHang() {
		WebUI.click(findTestObject("Bán Hàng/menu_GiaoDich"))
		WebUI.click(findTestObject("Bán Hàng/sub_ManHinhThuNgan"))
		WebUI.delay(3)
	}

	def moDanhSachDonHang() {
		WebUI.click(findTestObject("Bán Hàng/menu_GiaoDich"))
		WebUI.click(findTestObject("Bán Hàng/DanhSachDonHang/sub_DanhSachDonHang"))
		WebUI.delay(3)
	}

	def veTrangQuanLy() {
		WebUI.click(findTestObject('Bán Hàng/Icon_QuayLaiManHinh'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Bán Hàng/Sub_QuayVeTrangQuanLy'))
		WebUI.waitForPageLoad(2)
	}

	def searchProductByMa(String ma) {
		def obj = findTestObject('Bán hàng/DanhSachDonHang/HoaDon/txt_TimKiemMaCT')

		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)

		WebUI.click(obj)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}

	def chonNguoiBan(String user, boolean expectedVisible) {

		TestObject dropdown = findTestObject('Bán Hàng/TaoDonHang/NhanVienBanHang/icon_ChonNhanVien')
		TestObject txtSearch = findTestObject('Bán Hàng/TaoDonHang/NhanVienBanHang/txt_TimKiemNhanVien')

		WebUI.click(dropdown)
		WebUI.setText(txtSearch, user)
		WebUI.delay(1)
		WebUI.sendKeys(txtSearch, Keys.chord(Keys.ENTER))
		WebUI.delay(1)

		String actual = WebUI.getText(dropdown).trim()

		if (expectedVisible) {
			assert actual.equalsIgnoreCase(user)
		} else {
			assert !actual.equalsIgnoreCase(user)
		}
	}

	def nhapMaCT(maCT) {
		WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/txt_MaDonHang'), maCT)
		WebUI.waitForPageLoad(1)
	}
	def nhapGhiChu(String ghiChu) {
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/GhiChu/but_GhiChu'))
		WebUI.waitForElementVisible(findTestObject('Bán Hàng/TaoDonHang/GhiChu/txt_GhiChu'), 5)
		WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/GhiChu/txt_GhiChu'), ghiChu)
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/click_out'))
	}

	def nhapVaLayNgayTao(String ngay = null) {
		TestObject obj = findTestObject('Bán hàng/TaoDonHang/NgayTao/date_NgayTao')
		WebUI.waitForElementVisible(obj, 5)
		if (ngay) {
			WebUI.clearText(obj)
			WebUI.setText(obj, ngay)
			return WebUI.getAttribute(obj, "value")?.trim()
		}
		return WebUI.getAttribute(obj, "placeholder")?.trim()
	}

	def DoiTraHang(String maChungTu, String soLuongMoi) {

		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/DoiTraHang/but_DoiTraHang'))
		WebUI.waitForPageLoad(3)

		TestObject inputMa = findTestObject('Bán Hàng/TaoDonHang/DoiTraHang/txt_MaChungTu')
		WebUI.waitForElementVisible(inputMa, 5)
		WebUI.setText(inputMa, maChungTu)
		WebUI.sendKeys(inputMa, Keys.chord(Keys.ENTER))

		TestObject iconEdit = findTestObject('Bán Hàng/TaoDonHang/DoiTraHang/icon_ChinhSua')
		WebUI.waitForElementClickable(iconEdit, 10)
		WebUI.click(iconEdit)

		TestObject numSoLuong = findTestObject('Bán Hàng/TaoDonHang/DoiTraHang/num_SoLuong')
		WebUI.waitForElementVisible(numSoLuong, 5)
		WebUI.click(numSoLuong)
		WebUI.clearText(numSoLuong)
		WebUI.setText(numSoLuong, soLuongMoi)
		WebUI.sendKeys(numSoLuong, Keys.chord(Keys.ENTER))
		WebUI.comment("Đã sửa số lượng = " + soLuongMoi)
	}


	def timVaThemSanPham(String tenHang, String soLuong) {
		WebUI.setText(findTestObject("Bán Hàng/TaoDonHang/txt_TimKiemHang"), tenHang)
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject("Bán Hàng/TaoDonHang/txt_TimKiemHang"), Keys.chord(Keys.ENTER))
		WebUI.delay(2)
		TestObject objSoLuong = findTestObject("Bán Hàng/TaoDonHang/LuoiDanhSach/num_SoLuong", ["rowIndex": 1])
		WebUI.setText(objSoLuong, soLuong)
		WebUI.sendKeys(objSoLuong, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
		WebUI.comment("Đã thêm: ${tenHang} - SL: ${soLuong}")
	}

	def timVaThemSanPhamNhieuDong(String tenHang, String soLuong) {
		WebUI.setText(findTestObject("Bán Hàng/TaoDonHang/txt_TimKiemHang"), tenHang)
		WebUI.sendKeys(findTestObject("Bán Hàng/TaoDonHang/txt_TimKiemHang"), Keys.chord(Keys.ENTER))
		WebUI.delay(2)
		TestObject obj = findTestObject("Bán Hàng/TaoDonHang/LuoiDanhSach/num_SoLuong",["rowIndex": 1])
		WebUI.click(obj)
		WebUI.setText(obj, soLuong)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}

	def nhapKhachHang(String keyword) {
		TestObject txtSearch = findTestObject('Bán Hàng/TaoDonHang/KhachHang/txt_TimKhachHang')
		WebUI.clearText(txtSearch)
		WebUI.setText(txtSearch, keyword)
		WebUI.delay(2)
		WebUI.sendKeys(txtSearch, Keys.chord(Keys.ENTER))
		WebUI.delay(1)
	}

	def nhapChietKhauPhanTram(String percent) {
		TestObject input = findTestObject('Bán Hàng/TaoDonHang/ChietKhau/input_PhanTramChietKhau')
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/icon_ChietKhau'))
		WebUI.waitForElementVisible(input, 5)
		WebUI.click(input)
		WebUI.clearText(input)
		WebUI.setText(input, percent)
		WebUI.sendKeys(input, Keys.chord(Keys.ENTER))
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/click_out'))
		WebUI.delay(1)
	}

	def nhapChietKhauTien(String amount) {
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/icon_ChietKhau'))
		WebUI.waitForElementVisible(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/input_TienChietKhau'), 5)
		WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/input_TienChietKhau'), amount)
		WebUI.sendKeys(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/input_TienChietKhau'), Keys.chord(Keys.ENTER))
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/click_out'))
		WebUI.waitForPageLoad(2)
	}

	def chonChietKhauNhanh(String percent) {
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/icon_ChietKhau'))
		WebUI.waitForElementVisible(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/input_PhanTramChietKhau'), 5)
		String path = "Bán Hàng/TaoDonHang/ChietKhau/but_ChietKhau${percent}"
		WebUI.click(findTestObject(path))
		WebUI.delay(1)
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/click_out'))
		WebUI.waitForPageLoad(2)
	}

	def nhapMaGiamGia(String ma) {
		WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/GiamGia/txt_TimKiemMaGiam'), ma)
		WebUI.sendKeys(findTestObject('Bán Hàng/TaoDonHang/GiamGia/txt_TimKiemMaGiam'), Keys.chord(Keys.ENTER))
		WebUI.delay(2)
	}

	def nhapVAT(String vat) {
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/VAT/but_VAT'))
		WebUI.waitForElementVisible(findTestObject('Bán Hàng/TaoDonHang/VAT/num_VAT'), 5)
		WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/VAT/num_VAT'), vat)
		WebUI.sendKeys(findTestObject('Bán Hàng/TaoDonHang/VAT/num_VAT'), Keys.chord(Keys.ENTER))
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/ChietKhau/click_out'))
		WebUI.delay(2)
	}

	def checkTBLoi() {
		boolean isVisible = WebUI.waitForElementVisible(findTestObject('Bán hàng/TaoDonHang/err_KhachHang'), 5, FailureHandling.OPTIONAL)
		if (isVisible) {
			return true
		} else {
			return false
		}
	}

	def double getTienChietKhau() {
		TestObject obj = findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_ChietKhau')
		WebUI.waitForElementVisible(obj, 5)
		WebUI.waitForElementAttributeValue(obj, "value", ".+", 5)
		String value = WebUI.getAttribute(obj, "value")
		WebUI.comment("CK VALUE = " + value)
		return parseMoney(value)
	}

	def double getTienGiamGia() {
		return parseMoney(WebUI.getText(findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_TienMaGiam')))
	}

	def double getVAT() {
		String value = WebUI.getAttribute(findTestObject('Bán Hàng/TaoDonHang/VAT/but_VAT'), 'value')
		return parseMoney(value)
	}

	def double getTongThanhTien() {
		return parseMoney(WebUI.getText(findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_TongThanhTien')))
	}

	def double getTongCong() {
		return parseMoney(WebUI.getText(findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_TongCong')))
	}


	def double tinhTongCongExpected(double tongThanhTien, double tienChietKhau, double tienGiamGia) {
		return tongThanhTien - tienChietKhau - tienGiamGia
	}

	def verifyThongTinKhachHang(String ten = null, String sdt = null) {

		TestObject objTen = findTestObject('Bán Hàng/TaoDonHang/KhachHang/txt_TenKhachHang')
		TestObject objSDT = findTestObject('Bán Hàng/TaoDonHang/KhachHang/num_SĐT')
		WebUI.waitForElementVisible(objTen, 5)
		String actualTen = WebUI.getText(objTen).trim()
		String actualSDT = WebUI.getText(objSDT).replaceAll("[^0-9]", "")
		WebUI.comment("UI → Tên: " + actualTen + " | SĐT: " + actualSDT)
		if (ten != null) {
			assert actualTen.equalsIgnoreCase(ten) :"Sai tên KH | expected=" + ten + " | actual=" + actualTen
		}
		if (sdt != null) {
			assert actualSDT.contains(sdt) :"Sai SĐT | expected=" + sdt + " | actual=" + actualSDT
		}
		WebUI.comment(" Verify KH thành công")
	}


	def double verifyThanhTienTungDong() {
		List<WebElement> listRows = WebUI.findWebElements(findTestObject('Bán Hàng/TaoDonHang/LuoiDanhSach/xpath_AllProductRows'), 10)

		double tong = 0
		boolean loi = false

		for (int i = 0; i < listRows.size(); i++) {
			int row = i + 1

			double donGia = parseMoney(WebUI.findWebElement(findTestObject('Bán Hàng/TaoDonHang/LuoiDanhSach/num_DonGia', ['rowIndex': row]), 10).getAttribute('value'))
			double soLuong = parseMoney(WebUI.findWebElement(findTestObject('Bán Hàng/TaoDonHang/LuoiDanhSach/num_SoLuong', ['rowIndex': row]), 10).getAttribute('value'))
			double thanhTienUI = parseMoney(WebUI.findWebElement(findTestObject('Bán Hàng/TaoDonHang/LuoiDanhSach/col_DanhSachThanhTien', ['rowIndex': row]), 10).getText())

			double tinh = donGia * soLuong
			tong += tinh

			if (Math.abs(tinh - thanhTienUI) > 1) {
				loi = true
				WebUI.takeScreenshot("Loi_Dong_${row}")
			}
		}

		if (loi) assert false : "Sai thành tiền từng dòng"

		return tong
	}

	def void verifyTongThanhTien(double tongDong) {
		double tongUI = getTongThanhTien()

		if (Math.abs(tongDong - tongUI) > 1) {
			WebUI.takeScreenshot("Loi_TongThanhTien")
			assert false : "Sai tổng thành tiền"
		}
	}

	//	def void verifyTienChietKhau(double valueNhap, boolean isPercent) {
	//
	//		double tongThanhTien = getTongThanhTien()
	//		double tienCKUI = getTienChietKhau()
	//
	//		double expected = isPercent ? (tongThanhTien * valueNhap / 100) : valueNhap
	//
	//		expected = Math.min(expected, tongThanhTien)
	//
	//		WebUI.comment("Expected CK = " + expected + " | UI = " + tienCKUI)
	//
	//		if (Math.abs(expected - tienCKUI) > 1) {
	//			WebUI.takeScreenshot("Loi_ChietKhau")
	//			assert false : "Sai chiết khấu | expected=" + expected + " | UI=" + tienCKUI
	//		}
	//	}

	def void verifyTienChietKhau(double valueNhap, boolean isPercent) {

		double tongThanhTien = getTongThanhTien()
		double tienCKUI = getTienChietKhau()
		double tienGiamGia = getTienGiamGia()

		double expectedCK = isPercent ? (tongThanhTien * valueNhap / 100) : valueNhap
		expectedCK = Math.min(expectedCK, tongThanhTien)

		// 🔥 FIX 1: cộng voucher
		double expectedTotal = expectedCK + tienGiamGia

		// 🔥 FIX 2: không vượt quá tiền hàng
		expectedTotal = Math.min(expectedTotal, tongThanhTien)

		WebUI.comment("Expected CK = " + expectedTotal + " | UI = " + tienCKUI)

		if (Math.abs(expectedTotal - tienCKUI) > 1) {
			WebUI.takeScreenshot("Loi_ChietKhau")
			assert false : "Sai chiết khấu | expected=" + expectedTotal + " | UI=" + tienCKUI
		}
	}
	def void verifyChietKhauCoVoucher(double valueNhap, boolean isPercent) {

		double tongThanhTien = getTongThanhTien()
		double tienCKUI = getTienChietKhau()
		double tienGiamGia = getTienGiamGia()
		double expected = isPercent ? (tongThanhTien * valueNhap / 100) : valueNhap
		expected = Math.min(expected, tongThanhTien)

		WebUI.comment("Tổng = " + tongThanhTien)
		WebUI.comment("Voucher = " + tienGiamGia)
		WebUI.comment("Expected CK = " + expected)
		WebUI.comment("UI CK = " + tienCKUI)


		if (Math.abs(expected - tienCKUI) > 1) {
			WebUI.takeScreenshot("Loi_ChietKhau_CoVoucher")
			assert false : "Sai chiết khấu khi có voucher | expected=" + expected + " | UI=" + tienCKUI
		} else {
			WebUI.comment("Chiết khấu đúng khi có voucher")
		}
	}

	//	def void verifyTongCong(double tongThanhTien, double tienChietKhau, double tienGiamGia) {
	//
	//		double tongCongUI = getTongCong()
	//		double afterDiscount = tongThanhTien - tienChietKhau
	//		double tongCongExpected = Math.max(afterDiscount - tienGiamGia, 0)
	//		WebUI.comment("Expected Tổng cộng = " + tongCongExpected + " | UI = " + tongCongUI)
	//
	//		if (Math.abs(tongCongExpected - tongCongUI) > 2) {
	//			WebUI.takeScreenshot("Loi_TongCong")
	//			assert false : "Sai tổng cộng | expected=" + tongCongExpected + " | UI=" + tongCongUI
	//		}
	//	}
	def void verifyTongCong(double tongThanhTien, double tienChietKhau, double tienGiamGia, double vat) {

		double tongCongUI = getTongCong()

		double afterDiscount = tongThanhTien - tienChietKhau
		double afterVoucher = Math.max(afterDiscount - tienGiamGia, 0)

		double tongCongExpected = afterVoucher +vat

		WebUI.comment("Expected Tổng cộng = " + tongCongExpected + " | UI = " + tongCongUI)

		if (Math.abs(tongCongExpected - tongCongUI) > 2) {
			WebUI.takeScreenshot("Loi_TongCong")
			assert false : "Sai tổng cộng | expected=" + tongCongExpected + " | UI=" + tongCongUI
		}
	}

	//	def void verifyToanBoTien(double discountValue, boolean isPercent) {
	//		double tongDong = verifyThanhTienTungDong()
	//		verifyTongThanhTien(tongDong)
	//		verifyTienChietKhau(discountValue, isPercent)
	//		verifyTongCong()
	//	}
	//	def void verifyToanBoTien(double discountValue, boolean isPercent) {
	//
	//			double tongDong = verifyThanhTienTungDong()
	//			verifyTongThanhTien(tongDong)
	//			verifyTienChietKhau(discountValue, isPercent)
	//			double tongThanhTien = getTongThanhTien()
	//			double tienChietKhau = getTienChietKhau()
	//			double tienGiamGia = getTienGiamGia()
	//			verifyTongCong(tongThanhTien, tienChietKhau, tienGiamGia)
	//		}
	def void verifyToanBoTien(double discountValue, boolean isPercent) {

		double tongDong = verifyThanhTienTungDong()
		verifyTongThanhTien(tongDong)
		verifyTienChietKhau(discountValue, isPercent)
		double tongThanhTien = getTongThanhTien()
		double tienChietKhau = getTienChietKhau()
		double tienGiamGia = getTienGiamGia()
		double vat = getVAT()
		verifyTongCong(tongThanhTien, tienChietKhau, tienGiamGia, vat)
	}

	def verifyMoManHinhIn() {

		int before = WebUI.getWindowIndex()
		WebUI.delay(2)
		WebUI.switchToWindowIndex(before + 1)
		String url = WebUI.getUrl()
		assert url.contains("print") : "Không phải màn hình in"
		WebUI.comment("Màn hình in đã mở")
	}

	//thuộc danh sách đơn hàng
	def verifyMaCT(String maCT) {
		String v = WebUI.getText(findTestObject('Bán hàng/DanhSachDonHang/HoaDon/txt_MaCT'))?.trim()
		assert v.contains(maCT)
	}
	def verifyNguoiBan(String nguoiBan) {
		String v = WebUI.getText(findTestObject('Bán hàng/DanhSachDonHang/HoaDon/txt_NguoiBan'))?.trim()
		assert v.contains(nguoiBan)
	}
	def verifyTongCong(String tongCong) {
		def v = WebUI.getText(findTestObject('Bán hàng/DanhSachDonHang/HoaDon/num_TongCong'))
		def actual = v.replace(",", "").trim().toLong()
		def expected = tongCong.toLong()
		assert actual == expected :" Sai tổng tiền. UI: ${actual} | Expected: ${expected}"
	}
	def verifyKhachHang(String kh) {
		def v = WebUI.getText(findTestObject('Bán hàng/DanhSachDonHang/HoaDon/txt_KhachHang'))
		assert v != null : "Không lấy được tên khách hàng"
		assert v.toLowerCase().contains(kh.toLowerCase()) :"Sai KH. UI: ${v} | Expected: ${kh}"
	}

	def verifyNgayTao(String ngay) {
		String v = WebUI.getText(findTestObject('Bán hàng/DanhSachDonHang/HoaDon/date_NgayTao'))?.trim()

		assert v.split(" ")[0] == ngay.split(" ")[0]
	}

	//	def verifyNgayTao(String ngay) {
	//		String v = WebUI.getText(findTestObject('Bán hàng/DanhSachDonHang/HoaDon/date_NgayTao'))?.trim()
	//		assert v.contains(ngay)
	//	}

	//	def validateTextBox(String testObjectPath,String expectedValue,boolean shouldMatch) {
	//		try {
	//			String actual = WebUI.getAttribute(findTestObject(testObjectPath),'value')
	//			boolean actualResult = actual.trim() == expectedValue.trim()
	//			assert actualResult == shouldMatch
	//		}
	//		catch(Exception e){
	//			WebUI.takeScreenshot()
	//			throw e
	//		}
	//	}
	//
	//
	//	def validateNumber(path, expectedValue, shouldMatch){
	//
	//		String actual = WebUI.getAttribute(findTestObject(path), 'value')
	//
	//		boolean actualResult
	//
	//		if(isNumeric(actual) && isNumeric(expectedValue)){
	//			actualResult =
	//					new BigDecimal(formatData(actual))
	//					.compareTo(
	//					new BigDecimal(formatData(expectedValue))
	//					) == 0
	//		}
	//		else{
	//			actualResult =
	//					formatData(actual) == formatData(expectedValue)
	//		}
	//
	//		assert actualResult == shouldMatch
	//	}

	def validateTextBox(String testObjectPath, String expectedValue, boolean shouldMatch) {
		try {
			TestObject obj = findTestObject(testObjectPath)

			WebUI.waitForElementVisible(obj, 10)
			WebUI.click(obj)

			// 🔥 CLEAR CHUẨN (thay vì clearText)
			WebUI.sendKeys(obj, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(obj, Keys.chord(Keys.DELETE))

			// nhập lại để đảm bảo state sạch
			WebUI.setText(obj, expectedValue)

			// lấy lại value sau khi set
			String actual = WebUI.getAttribute(obj, 'value')?.trim()

			boolean actualResult = actual == expectedValue.trim()

			assert actualResult == shouldMatch :
			"Textbox sai | expected=${expectedValue} | actual=${actual}"
		} catch (Exception e) {
			WebUI.takeScreenshot()
			throw e
		}
	}
	def validateNumber(String path, String expectedValue, boolean shouldMatch){

		TestObject obj = findTestObject(path)

		WebUI.waitForElementVisible(obj, 10)
		WebUI.click(obj)

		// 🔥 CLEAR CHUẨN
		WebUI.sendKeys(obj, Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(obj, Keys.chord(Keys.DELETE))

		WebUI.setText(obj, expectedValue)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))

		String actual = WebUI.getAttribute(obj, 'value')

		boolean actualResult

		if(isNumeric(actual) && isNumeric(expectedValue)){
			actualResult =
					new BigDecimal(formatData(actual))
					.compareTo(new BigDecimal(formatData(expectedValue))) == 0
		}
		else{
			actualResult =
					formatData(actual) == formatData(expectedValue)
		}

		assert actualResult == shouldMatch :
		"Number sai | expected=${expectedValue} | actual=${actual}"
	}

	def isNumeric(String v){
		if(v == null) return false

		v = v.replaceAll(",", "")

		return v ==~ /^\d+(\.\d+)?$/
	}

	def formatData(String value) {
		if (value == null) return ""
		String cleanValue = value.replaceAll(",", "")
		if (cleanValue.endsWith(".0")) {
			cleanValue = cleanValue.substring(0, cleanValue.length() - 2)
		}
		return cleanValue.trim()
	}


	def parseMoney(String text) {
		if (!text) return 0

		String clean = text.replace(',', '').replace('.', '').replace('₫', '').trim()

		try {
			return Double.parseDouble(clean)
		} catch (Exception e) {
			return 0
		}
	}

	def clickSave() {
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/but_Luu'))
	}

	def clickPrint() {
		WebUI.click(findTestObject('Bán Hàng/TaoDonHang/but_In'))
		WebUI.waitForPageLoad(2)
	}

	def dongManHinhIn() {
		WebUI.delay(2)
		try {
			WebUI.closeWindowIndex(WebUI.getWindowIndex())
			WebUI.switchToWindowIndex(0)
		} catch (Exception e) {
			WebUI.sendKeys(null, Keys.chord(Keys.ESCAPE))
		}
	}
}