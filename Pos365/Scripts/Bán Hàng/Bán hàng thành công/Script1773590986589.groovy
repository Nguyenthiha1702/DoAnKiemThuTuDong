import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

//================================================
// Hàm chuyển tiền về số
//================================================
//================================================
// 1 LOGIN
//================================================
WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)

//================================================
// 2 MỞ MÀN HÌNH BÁN HÀNG
//================================================
WebUI.click(findTestObject('Bán Hàng/menu_GiaoDich'))

WebUI.click(findTestObject('Bán Hàng/sub_ManHinhThuNgan'))

WebUI.delay(3)

//================================================
// 3 THÊM SẢN PHẨM
//================================================
WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/txt_TimKiemHang'), 'hàng 1')

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Bán Hàng/TaoDonHang/txt_TimKiemHang'), Keys.chord(Keys.ENTER))

WebUI.delay(3)

WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/txt_TimKiemHang'), 'hàng 2')

WebUI.delay(2)

WebUI.sendKeys(findTestObject('Bán Hàng/TaoDonHang/txt_TimKiemHang'), Keys.chord(Keys.ENTER))

WebUI.delay(3)

//================================================
// 4 LẤY DANH SÁCH DÒNG SẢN PHẨM
//================================================
List<WebElement> listRows = WebUI.findWebElements(findTestObject('Bán Hàng/TaoDonHang/LuoiDanhSach/xpath_AllProductRows'), 10)

println(('Có ' + listRows.size()) + ' sản phẩm trong giỏ')

//================================================
// 5 KIỂM TRA TỪNG DÒNG
//================================================
double tongTinhToan = 0

boolean coLoiDong = false

for (int i = 0; i < listRows.size(); i++) {
    int row = i + 1

    WebElement inputDonGia = WebUI.findWebElement(findTestObject('Bán Hàng/num_DonGia', [('rowIndex') : row]), 10)

    WebElement inputSoLuong = WebUI.findWebElement(findTestObject('Bán Hàng/num_SoLuong', [('rowIndex') : row]), 10)

    WebElement thanhTienElement = WebUI.findWebElement(findTestObject('Bán Hàng/col_DanhSachThanhTien', [('rowIndex') : row]), 
        10)

    //--------------------------------------------
    // LẤY TEXT
    //--------------------------------------------
    String strDonGia = inputDonGia.getAttribute('value')

    String strSoLuong = inputSoLuong.getAttribute('value')

    String strThanhTien = thanhTienElement.getText()

    //--------------------------------------------
    // DEBUG
    //--------------------------------------------
    println('Row ' + row)

    println('Don gia raw = ' + strDonGia)

    println('So luong raw = ' + strSoLuong)

    println('Thanh tien UI raw = ' + strThanhTien)

    //--------------------------------------------
    // CHUYỂN SANG SỐ
    //--------------------------------------------
    double donGia = parseMoney(strDonGia)

    double soLuong = parseMoney(strSoLuong)

    double thanhTienUI = parseMoney(strThanhTien)

    double thanhTienTinh = donGia * soLuong

    //--------------------------------------------
    // SO SÁNH
    //--------------------------------------------
    if (Math.abs(thanhTienTinh - thanhTienUI) > 1) {
        coLoiDong = true

        WebUI.comment((((((((('Dòng ' + row) + ' SAI: ') + donGia) + ' x ') + soLuong) + ' = ') + thanhTienTinh) + ' nhưng UI = ') + 
            thanhTienUI)

        WebUI.takeScreenshot('Loi_Dong_' + row)
    } else {
        println(('Row ' + row) + ' OK')
    }
    
    tongTinhToan += thanhTienTinh
}

//================================================
// 6 LẤY TỔNG TIỀN
//================================================
String textTongCong = WebUI.getText(findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_TongCong'))

println('Tong UI raw = ' + textTongCong)

double tongUI = parseMoney(textTongCong)

println('Tong tinh toan = ' + tongTinhToan)

println('Tong UI = ' + tongUI)

//================================================
// 7 ASSERT
//================================================
if ((Math.abs(tongTinhToan - tongUI) > 1) || coLoiDong) {
    WebUI.takeScreenshot('Loi_Tong_Cong')

    WebUI.comment((('LỖI: Tổng tính = ' + tongTinhToan) + ' nhưng UI = ') + tongUI)

    assert false : 'Tổng tiền không khớp'
} else {
    println('PASS: Tất cả dòng và tổng cộng đều đúng')
}

WebUI.delay(2)

WebUI.click(findTestObject('Bán Hàng/TaoDonHang/but_Luu'))

double parseMoney(String text) {
    text = text.replace(',', '').replace('₫', '').trim()

    return Double.parseDouble(text)
}

