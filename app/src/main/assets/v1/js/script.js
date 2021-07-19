/* script.js */
const api_domain = 'http://www.mangomap.co.kr';
$.datepicker.setDefaults({
	dateFormat: 'yy-mm-dd',
	prevText: '이전 달',
	nextText: '다음 달',
	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	showMonthAfterYear: true,
	yearSuffix: '년',
    showOtherMonths: true,
    selectOtherMonths: true,
    maxDate: 0
});
const touching = null;
let init = ()=>{};
function longTouch(e) {
    // do something!
}
function clickTabBarItem(ele) {
    const clickable = $(ele).data('clickable');
    if(clickable || clickable == 'true') {
        $(ele).parent().find('.TabBar-Item-Index').removeClass('selected');
        $(ele).find('.TabBar-Item-Index').addClass('selected');
        onSelectedTabBarItem(ele);
    }
}
let onSelectedTabBarItem = (ele) => {};
function getLoginedId() {
    return WebView.getId();
}
function getLoginedName() {
    return WebView.getName();
}
function showLoading() {
    WebView.showLoading();
}
function hideLoading() {
    WebView.hideLoading();
}
function date(date, div){
    div = div || '-';
    let d = new Date();
    if(date != undefined){
        d = new Date(date);
    }
    let month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join(div);
}
Number.prototype.toDigit = function() {
    const str = '' + new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(this);
    return (str.startsWith('-') ? '-' + str.substr(2) : str.substr(1));
}
String.prototype.toDate = function(delim) {
    delim = delim || '-';
    return `${this.slice(0,4)}${delim}${this.slice(4,6)}${delim}${this.slice(6)}`;
};
String.prototype.toDigit = function() {
    return parseInt(this.replace(/[,]/g, '')).toDigit();
}
String.prototype.toHtml = function() {
    return this.replace(/(\n|\r\n)/g, '<br/>');
}
const qs = (() => {
    const a = window.location.search.substr(1).split('&');
    if (a == "") return {};
    let b = {};
    for (let i = 0; i < a.length; ++i) {
        const p = a[i].split('=', 2);
        b[p[0]] = (p.length == 1) ? '' : decodeURIComponent(p[1].replace(/\+/g, " "));
    }
    return b;
})();
$(window).on("load", () => {

    // 선택 취소
    $('selector').each(function() {
        this.addEventListener("touchstart", function(e) {
            e.preventDefault();
            touching = window.setTimeout(longTouch, 500, true);
        }, false);
        this.addEventListener("touchend", function(e) {
            e.preventDefault();
            window.clearTimeout(touching);
        }, false);
    });

    // 액션바 뒤로 버튼 클릭
    $('.ActionBar-GoBack').click(function(){
        WebView.closeActivity();
    });

    // 입력 폼 다음으로 넘기기
    $('input').on('keydown', function(e) {
        console.log(`event.keyCode=${e.keyCode}`);
        const next = $(this).data('next');
        if(next && e.keyCode == 13) {
            $(`#${next}`).focus();
        }
    });

    // 날짜 입력 폼
    $('input[type=text].date').datepicker();
    $('input[type=text].date').prop('readonly', true);
    $(".Calendar-Button").on('click', function(e){
        $('#' + $(this).data('target')).datepicker("show");
    });

    // 탭바 클릭
    $('.TabBar-Item').on('click', function(e) {
        clickTabBarItem(this);
    });

    // 숫자입력
    $('div').on('focus', 'input.digit', function(){
        const val = $(this).val().replace(/[,]/g, '');
        $(this).val(val);
        console.log('input.digit focus', $(this).val());
    });
    $('div').on('blur', 'input.digit', function(){
        const val = $(this).val().replace(/[,]/g, '');
        $(this).val(val.toDigit());
        console.log('input.digit blur', $(this).val());
    });

    // ready
    (()=>init())();

});
function openActivity(str, args) {
    switch(str) {
        case '로그아웃':
            WebView.setGrant("");
            WebView.openLoginActivity();
            break;
        case '3자물류:메인':
            WebView.openUseCase1HomeActivity();
            break;
        case '3자물류:공지사항':
            WebView.openUseCase1Menu1Activity();
            break;
        case '3자물류:주문현황':
            WebView.openUseCase1Menu2Activity();
            break;
        case '3자물류:주문현황(상세)':
            WebView.openUseCase1Menu21Activity(args[0], args[1], args[2], args[3]);
            break;
        case '3자물류:단가정보':
            WebView.openUseCase1Menu3Activity();
            break;
        case '경영정보:메인':
            WebView.openUseCase2HomeActivity();
            break;
        case '경영정보:주문현황':
            WebView.openUseCase2Menu1Activity();
            break;
        case '경영정보:판매현황':
            WebView.openUseCase2Menu2Activity();
            break;
        case '경영정보:이익현황':
            WebView.openUseCase2Menu3Activity();
            break;
        case '경영정보:미수금현황':
            WebView.openUseCase2Menu4Activity();
            break;
        case '경영정보:자금현황':
            WebView.openUseCase2Menu5Activity();
            break;
        case '경영정보:자금현황상세':
            WebView.openUseCase2Menu51Activity(args[0], args[1]);
            break;
        case '점검관리:NfcReader':
            WebView.openUseCase3NfcReaderActivity();
            break;
        case '점검관리:메인':
            WebView.openUseCase3HomeActivity();
            break;
        case '점검관리:평가이력':
            WebView.openUseCase3Menu1Activity();
            break;
        case '점검관리:가맹점정보':
            WebView.openUseCase3Menu2Activity(args[0], args[1]);
            break;
        case '점검관리:가맹점평가':
            WebView.openUseCase3Menu3Activity(args[0], args[1]);
            break;
        case '점검관리:가맹점평가완료':
            WebView.openUseCase3Menu32Activity(args[0], args[1]);
            break;
        case '점검관리:세부기준':
            WebView.openUseCase3Menu33Activity(args[0], args[1], args[2], args[3], args[4]);
            break;
        case '점검관리:지도':
            WebView.openUseCase3Menu34Activity(args[0], args[1], args[2]);
            break;
        default:
            alert('요청하신 서비스는 준비중입니다.\n빠른 시일내에 제공해 드리겠습니다.');
            break;
    }
}