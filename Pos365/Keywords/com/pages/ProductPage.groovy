package com.pages
import com.kms.katalon.core.testobject.ConditionType
import java.math.BigDecimal
import org.openqa.selenium.Keys
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
public class ProductPage {



	def navigateToList() {
		WebUI.click(findTestObject('Hàng hóa/Menu/menu_HangHoa'))
		WebUI.click(findTestObject('Hàng hóa/Menu/sub_DanhSachHangHoa'))
	}

	def navigateToCreate() {
		WebUI.click(findTestObject('Hàng hóa/Menu/menu_HangHoa'))
		WebUI.click(findTestObject('Hàng hóa/Menu/sub_DanhSachHangHoa'))
		WebUI.click(findTestObject('Hàng hóa/Menu/but_ThemMoi'))
	}

	def createProduct(
			String ma,
			String ten,
			String giaBan,
			String giaVon,
			String tonKho,
			String dvt) {
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_MaHangHoa'), ma)
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_TenHangHoa'), ten)
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_GiaBan'), formatData(giaBan))
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_GiaVon'), formatData(giaVon))
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_TonKho'), formatData(tonKho))
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_DVT'), dvt)
	}

	def createProductKhongMa(
			String ten,
			String giaBan,
			String giaVon,
			String tonKho,
			String dvt) {
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_TenHangHoa'), ten)
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_GiaBan'), formatData(giaBan))
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_GiaVon'), formatData(giaVon))
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_TonKho'), formatData(tonKho))
		WebUI.setText(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_DVT'), dvt)
	}



	def uploadImage(String fileName) {
		String filePath = RunConfiguration.getProjectDir() + "/Data Files/Product/" + fileName
		WebUI.click(findTestObject('Object Repository/Hàng hóa/ThemMoi/ChiTiet/but_HinhAnh'))
		WebUI.uploadFile(findTestObject('Object Repository/Hàng hóa/ThemMoi/ChiTiet/upload_HinhAnh'), filePath)
	}

	def chonNhom(String tenNhom) {
		WebUI.click(findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/chonNhom'))
		WebUI.click(findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/option_ChonNhom', [('ten_nhom') : tenNhom]))
	}

	def selectLoaiHang(String value) {

		WebUI.click(findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/txt_LoaiHang'))

		switch (value) {
			case 'Hàng hóa':
				WebUI.click(findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/op_HangHoa'))
				break

			case 'Dịch vụ':
				WebUI.click(findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/op_DichVu'))
				break

			case 'Combo':
				WebUI.click(findTestObject('Hàng hóa/ThemMoi/ChiTiet/ThongTinKhac/op_Combo'))
				break

			default:
				throw new Exception("Không có loại hàng: " + value)
		}
	}



	def clickFix() {
		WebUI.click(findTestObject('Hàng hóa/Sua/but_CapNhat'))
		WebUI.waitForPageLoad(2)
	}

	def clickSave() {
		WebUI.click(findTestObject('Hàng hóa/ThemMoi/but_Luu'))
		WebUI.waitForPageLoad(2)
	}
	def clickDelete() {
		WebUI.click(findTestObject('Hàng hóa/Xoa/but_Xoa'))
		WebUI.waitForPageLoad(2)
	}
	def clickYes() {
		WebUI.click(findTestObject('Hàng hóa/Xoa/but_DongY'))
		WebUI.waitForPageLoad(2)
	}
	def clickNo() {
		WebUI.click(findTestObject('Hàng hóa/Xoa/but_ThoatXoa'))
		WebUI.waitForPageLoad(2)
	}
	def clickX() {
		WebUI.click(findTestObject('Hàng hóa/Xoa/icon_X'))
		WebUI.waitForPageLoad(2)
	}
	def clickExit() {
		WebUI.click(findTestObject('Hàng hóa/ThemMoi/but_Thoat'))
		WebUI.waitForPageLoad(2)
	}
	def clickSaveAndCopy() {
		WebUI.click(findTestObject('Hàng hóa/ThemMoi/but_LuuVaCopy'))
		WebUI.waitForPageLoad(2)
	}
	def clickSaveAndAdd() {
		WebUI.click(findTestObject('Hàng hóa/ThemMoi/but_LuuVaThemMoi'))
		WebUI.waitForPageLoad(2)
	}


	def searchProductByMa(String ma) {
		def obj = findTestObject('Hàng hóa/TimKiem/Txt_TimKiemTen')
		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)
		WebUI.scrollToElement(obj, 5)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
	}

	def searchProductByNCC(String ma) {
		def obj = findTestObject('Hàng hóa/TimKiem/Txt_TimKiemNCC')
		WebUI.waitForElementVisible(obj, 10)
		WebUI.waitForElementClickable(obj, 10)
		WebUI.scrollToElement(obj, 5)
		WebUI.clearText(obj)
		WebUI.setText(obj, ma)
		WebUI.sendKeys(obj, Keys.chord(Keys.ENTER))
	}

	def clickNhom(String tenNhom) {
		def obj = findTestObject('Hàng hóa/TimKiem/op_TenNhom', [('tenNhom') : tenNhom])
		WebUI.waitForElementVisible(obj, 10)
		WebUI.scrollToElement(obj, 5)
		WebUI.click(obj)
	}

	def clickCheckbox(String type) {
		def checkbox = findTestObject(
				type == "hanghoa" ? 'Hàng hóa/TimKiem/cbox_HangHoa' :
				type == "dichvu"  ? 'Hàng hóa/TimKiem/cbox_DichVu'  :
				type == "combo"   ? 'Hàng hóa/TimKiem/cbox_Combo'   :
				null
				)
		if (checkbox == null) {
			throw new Exception("Loại không hợp lệ: " + type)
		}
		WebUI.click(checkbox)
	}

	def verifyGroup(String groupName) {
		TestObject to = findTestObject('Hàng hóa/TimKiem/txt_tenNhom',[('groupName') : groupName])
		WebUI.waitForElementVisible(to, 10)
		WebUI.verifyElementPresent(to, 10)
	}


	def verifyProductFirstRow(String maHH) {
		TestObject to = new TestObject()

		String xpath = "//td[@data-field='Code']//div[@class='photo-name' and normalize-space()='${maHH}']"

		to.addProperty("xpath", ConditionType.EQUALS, xpath)

		WebUI.waitForElementPresent(to, 10)
		WebUI.verifyElementPresent(to, 10)
	}

	def verifyProductImage(String maHH, String expectedName) {
		TestObject img = findTestObject('Hàng hóa/XemChiTiet/img_Product',[('maHH') : maHH])
		WebUI.waitForElementVisible(img, 10)
		String style = WebUI.getAttribute(img, 'style')
		String url = style.replaceAll(".*url\\(\"?(.*?)\"?\\).*", "\$1")
		println "Image URL: " + url
		assert url.toLowerCase().contains(expectedName.toLowerCase())
	}

	def getKeyFromFileName(String fileName) {
		return fileName.split("\\.")[0]
	}

	def getProductInfoInGrid() {
		String ma = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/txt_MaHangHoaCT'))
		String ten = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/txt_TenHangHoaCT'))
		String giaBan = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/num_GiaBanCT'))
		String giaVon = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/num_GiaVonCT'))
		String dvt = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/txt_DvtCT'))

		WebUI.click(findTestObject('Hàng hóa/XemChiTiet/tab_TonKho'))
		WebUI.delay(1)
		String tonKho = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/num_TonKho'))
		WebUI.click(findTestObject('Hàng hóa/XemChiTiet/tab_ChiTiet'))
		return [
			ma    : ma,
			ten   : ten,
			giaBan: giaBan,
			giaVon: giaVon,
			dvt   : dvt,
			tonKho: tonKho
		]
	}

	def getTonKho() {

		WebUI.click(findTestObject('Hàng hóa/XemChiTiet/tab_TonKho'))
		WebUI.waitForElementVisible(findTestObject('Hàng hóa/XemChiTiet/num_TonKho'), 5)
		String raw = WebUI.getText(findTestObject('Hàng hóa/XemChiTiet/num_TonKho')).trim()
		int tonKho = raw.replaceAll("[^0-9]", "").toInteger()
		WebUI.click(findTestObject('Hàng hóa/XemChiTiet/tab_ChiTiet'))
		return tonKho
	}

	def verifyProduct(String expectedTen, String expectedMa, String expectedGiaBan, String expectedGiaVon, String expectedTonKho, String expectedDvt) {
		try {
			Map actual = getProductInfoInGrid()
			assert actual.ten.trim() == expectedTen.trim()
			assert actual.ma.trim() == expectedMa.trim()
			assert formatData(actual.giaBan) == formatData(expectedGiaBan)
			assert formatData(actual.giaVon) == formatData(expectedGiaVon)
			assert formatData(actual.tonKho) == formatData(expectedTonKho)
			assert actual.dvt == expectedDvt
		} catch (Exception e) {
			WebUI.takeScreenshot()
			throw e
		}
	}

	def verifyFirstProductCode(String expected) {
		TestObject to = findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien')
		WebUI.waitForElementVisible(to, 10)
		String actual = WebUI.getText(to)?.trim()
		assert actual == expected :"Sai mã hàng | expected=${expected} | actual=${actual}"
		return actual
	}
	def verifyFirstProductName(String expected) {
		TestObject to = findTestObject('Hàng hóa/XemChiTiet/row_TenHangDauTien')
		WebUI.waitForElementVisible(to, 10)
		String actual = WebUI.getText(to)?.trim()
		assert actual?.toLowerCase()?.contains(expected?.toLowerCase()) :
		" Sai tên hàng | expected chứa=${expected} | actual=${actual}"

		return actual
	}
	def getValuesOnForm() {
		String ma = WebUI.getAttribute(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_MaHangHoa'), 'value')
		String ten = WebUI.getAttribute(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_TenHangHoa'), 'value')
		String giaBan = WebUI.getAttribute(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_GiaBan'), 'value')
		String giaVon = WebUI.getAttribute(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_GiaVon'), 'value')
		String tonKho = WebUI.getAttribute(findTestObject('Hàng hóa/ThemMoi/ChiTiet/num_TonKho'), 'value')
		String dvt = WebUI.getAttribute(findTestObject('Hàng hóa/ThemMoi/ChiTiet/txt_DVT'), 'value')

		return [
			ma    : ma,
			ten   : ten,
			giaBan: giaBan,
			giaVon: giaVon,
			tonKho: tonKho,
			dvt   : dvt
		]
	}



	def checkNotificationVisible() {
		boolean isVisible = WebUI.waitForElementVisible(findTestObject('Hàng hóa/ThemMoi/noti_NhapDuDL'), 5, FailureHandling.OPTIONAL)

		if (isVisible) {
			return true
		} else {
			return false
		}
	}

	def validateSearch(String ten, String ncc, String type) {
		type = type?.trim()?.toUpperCase()

		TestObject obj = (type == 'TEN') ?
				findTestObject('Object Repository/Hàng hóa/TimKiem/Txt_TimKiemTen') :
				findTestObject('Object Repository/Hàng hóa/TimKiem/Txt_TimKiemNCC')

		WebUI.waitForElementClickable(obj, 10)
		WebUI.click(obj)
		WebUI.clearText(obj)

		String value = (type == 'TEN') ? ten : ncc
		WebUI.setText(obj, value)

		assert WebUI.getAttribute(obj, 'value')?.trim() == value?.trim()
	}

	//	def validateSearch(String ten, String ncc, String type) {
	//		type = type.trim().toUpperCase()
	//		if (type == 'TEN') {
	//			WebUI.setText(findTestObject('Hàng hóa/TimKiem/Txt_TimKiemTen'), ten)
	//			WebUI.delay(1)
	//			String actual = WebUI.getAttribute(findTestObject('Hàng hóa/TimKiem/Txt_TimKiemTen'), 'value')
	//			assert actual == ten
	//		} else if (type == 'NCC') {
	//			WebUI.setText(findTestObject('Hàng hóa/TimKiem/Txt_TimKiemNCC'), ncc)
	//			WebUI.delay(1)
	//			String actual = WebUI.getAttribute(findTestObject('Hàng hóa/TimKiem/Txt_TimKiemNCC'), 'value')
	//			assert actual == ncc
	//		} else {
	//			WebUI.comment(" Sai type: " + type)
	//			assert false
	//		}
	//	}

	//	def validateProductInputFields(String tc_id, String ma,String ten,String giaBan,String giaVon,String tonKho,String dvt) {
	//		WebUI.comment("Tc: " + tc_id)
	//		try {
	//			Map actual = getValuesOnForm()
	//			assert actual.ma.trim() == ma.trim()
	//			assert actual.ten.trim() == ten.trim()
	//			assert formatData(actual.giaBan) == formatData(giaBan)
	//			assert formatData(actual.giaVon) == formatData(giaVon)
	//			assert formatData(actual.tonKho) == formatData(tonKho)
	//			assert actual.dvt.trim() == dvt.trim()
	//			WebUI.comment("Validate thành công")
	//		} catch(Exception e){
	//			WebUI.takeScreenshot()
	//			throw e
	//		}
	//	}

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
			actualResult =
					new BigDecimal(formatData(actual))
					.compareTo(
					new BigDecimal(formatData(expectedValue))
					) == 0
		}
		else{
			actualResult =
					formatData(actual) == formatData(expectedValue)
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
}