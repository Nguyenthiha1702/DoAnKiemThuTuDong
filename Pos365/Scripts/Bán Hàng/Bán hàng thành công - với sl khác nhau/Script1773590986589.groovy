import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//================================================
// 1. CHUẨN BỊ DỮ LIỆU & ĐĂNG NHẬP
//================================================
def data = findTestData('TestHangHoa' )
SellPage sellPage = new SellPage()

WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Bán Hàng/menu_GiaoDich'))

WebUI.click(findTestObject('Bán Hàng/sub_ManHinhThuNgan'))

WebUI.delay(3)

for (int i = 1; i <= data.getRowNumbers(); i++) {
    String tenHang = data.getValue('TenHang', i)

    String soLuong = data.getValue('SoLuong', i)

   
    WebUI.setText(findTestObject('Bán Hàng/TaoDonHang/txt_TimKiemHang'), tenHang)

    WebUI.delay(1)

    WebUI.sendKeys(findTestObject('Bán Hàng/TaoDonHang/txt_TimKiemHang'), Keys.chord(Keys.ENTER))

    WebUI.delay(1) 
    WebUI.setText(findTestObject('Bán Hàng/num_SoLuong', [('rowIndex') : 1]), soLuong)

	WebUI.sendKeys(findTestObject('Bán Hàng/num_SoLuong', [('rowIndex') : 1]), Keys.chord(Keys.ENTER))

    println(">>> Đã thêm: $tenHang - SL: $soLuong")
}

WebUI.delay(2)


List<WebElement> listRows = WebUI.findWebElements(findTestObject('Bán Hàng/TaoDonHang/LuoiDanhSach/xpath_AllProductRows'), 10)

println('Tổng số dòng sản phẩm hiện có: ' + listRows.size())

double tongTinhToan = 0

boolean coLoiDong = false

for (int i = 0; i < listRows.size(); i++) {
    int row = i + 1

    WebElement inputDonGia = WebUI.findWebElement(findTestObject('Bán Hàng/num_DonGia', [('rowIndex') : row]), 10)

    WebElement inputSoLuong = WebUI.findWebElement(findTestObject('Bán Hàng/num_SoLuong', [('rowIndex') : row]), 10)

    WebElement thanhTienElement = WebUI.findWebElement(findTestObject('Bán Hàng/col_DanhSachThanhTien', [('rowIndex') : row]), 
        10)

    String strDonGia = inputDonGia.getAttribute('value')

    String strSoLuong = inputSoLuong.getAttribute('value')

    String strThanhTien = thanhTienElement.getText()

    double donGia = parseMoney(strDonGia)

    double soLuong = parseMoney(strSoLuong)

    double thanhTienUI = parseMoney(strThanhTien)

    double thanhTienTinh = donGia * soLuong

    println("Dòng $row: Đơn giá=$donGia | SL=$soLuong | Thành tiền UI=$thanhTienUI")

    if (Math.abs(thanhTienTinh - thanhTienUI) > 1) {
        coLoiDong = true

        WebUI.comment("SAI TẠI DÒNG $row: Tính toán $thanhTienTinh nhưng UI hiện $thanhTienUI")

        WebUI.takeScreenshot("Loi_Dong_$row")
    }
    
    tongTinhToan += thanhTienTinh
}


String textTongCong = WebUI.getText(findTestObject('Bán Hàng/TaoDonHang/tab_Tien/num_TongCong'))

double tongUI = parseMoney(textTongCong)

println('--------------------------------------')

println('TỔNG TÍNH TOÁN: ' + tongTinhToan)

println('TỔNG TRÊN UI  : ' + tongUI)

println('--------------------------------------')

if ((Math.abs(tongTinhToan - tongUI) > 1) || coLoiDong) {
    WebUI.takeScreenshot('Loi_Ket_Qua_Cuoi')

    WebUI.comment('THẤT BẠI: Dữ liệu tính toán không khớp!')

    assert false : 'Tổng tiền hoặc chi tiết dòng không khớp'
} else {
    println('THÀNH CÔNG: Tất cả dữ liệu đều chính xác!')
}

WebUI.delay(4 )


WebUI.click(findTestObject('Bán Hàng/TaoDonHang/but_Luu'))


