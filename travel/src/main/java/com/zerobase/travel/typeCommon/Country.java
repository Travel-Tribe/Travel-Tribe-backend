package com.zerobase.travel.typeCommon;/*
 * Copyright (C) 2012 Neo Visionaries Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.HashMap;
import java.util.Map;
import lombok.Getter;


/**
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a>
 * country code.
 *
 * <p>
 * Enum names of this enum themselves are represented by
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
 * codes. There are instance methods to get the country name ({@link #getName()}), the
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3" >ISO 3166-1 alpha-3</a>
 * code ({@link #getAlpha3()}) and the
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric">ISO 3166-1 numeric</a>
 * code ({@link #getNumeric()}).
 * In addition, there are static methods to get a CountryCode instance that
 * corresponds to a given alpha-2/alpha-3/numeric code ({@link #getByCode(String)},
 * {@link #getByCode(int)}).
 * </p>
 *
 * <pre style="background-color: #EEEEEE; margin-left: 2em; margin-right: 2em; border: 1px solid black;">
 * <span style="color: darkgreen;">// EXAMPLE</span>
 *
 * CountryCode cc = CountryCode.{@link #getByCode(String) getByCode}("JP");
 *
 * <span style="color: darkgreen;">// Country name</span>
 * System.out.println("Country name = " + cc.{@link #getName()});                  <span style="color: darkgreen;">// "Japan"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-2 code</span>
 * System.out.println("ISO 3166-1 alpha-2 code = " + cc.{@link #getAlpha2()});     <span style="color: darkgreen;">// "JP"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-3 code</span>
 * System.out.println("ISO 3166-1 alpha-3 code = " + cc.{@link #getAlpha3()});     <span style="color: darkgreen;">// "JPN"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 numeric code</span>
 * System.out.println("ISO 3166-1 numeric code = " + cc.{@link #getNumeric()});    <span style="color: darkgreen;">// 392</span>
 * </pre>
 *
 * @author Takahiko Kawasaki
 */
@Getter
public enum Country
{
    // @formatter:off
    /** <a href="http://en.wikipedia.org/wiki/Andorra">Andorra</a> */
    AD("Andorra", "AND", 16, "안도라"),

    /** <a href="http://en.wikipedia.org/wiki/United_Arab_Emirates">United Arab Emirates</a> */
    AE("United Arab Emirates", "ARE", 784, "아랍에미리트"),

    /** <a href="http://en.wikipedia.org/wiki/Afghanistan">Afghanistan</a> */
    AF("Afghanistan", "AFG", 4, "아프가니스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Antigua_and_Barbuda">Antigua and Barbuda</a> */
    AG("Antigua and Barbuda", "ATG", 28, "앤티가 바부다"),

    /** <a href="http://en.wikipedia.org/wiki/Anguilla">Anguilla</a> */
    AI("Anguilla", "AIA", 660, "앵귈라"),

    /** <a href="http://en.wikipedia.org/wiki/Albania">Albania</a> */
    AL("Albania", "ALB", 8, "알바니아"),

    /** <a href="http://en.wikipedia.org/wiki/Armenia">Armenia</a> */
    AM("Armenia", "ARM", 51, "아르메니아"),

    /** <a href="http://en.wikipedia.org/wiki/Netherlands_Antilles">Netherlands Antilles</a> */
    AN("Netherlands Antilles", "ANT", 530, "네덜란드령 안틸레스"),

    /** <a href="http://en.wikipedia.org/wiki/Angola">Angola</a> */
    AO("Angola", "AGO", 24, "앙골라"),

    /** <a href="http://en.wikipedia.org/wiki/Antarctica">Antarctica</a> */
    AQ("Antarctica", "ATA", 10, "남극"),

    /** <a href="http://en.wikipedia.org/wiki/Argentina">Argentina</a> */
    AR("Argentina", "ARG", 32, "아르헨티나"),

    /** <a href="http://en.wikipedia.org/wiki/American_Samoa">American Samoa</a> */
    AS("American Samoa", "ASM", 16, "미국령 사모아"),

    /** <a href="http://en.wikipedia.org/wiki/Austria">Austria</a> */
    AT("Austria", "AUT", 40, "오스트리아"),

    /** <a href="http://en.wikipedia.org/wiki/Australia">Australia</a> */
    AU("Australia", "AUS", 36, "호주"),

    /** <a href="http://en.wikipedia.org/wiki/Aruba">Aruba</a> */
    AW("Aruba", "ABW", 533, "아루바"),

    /** <a href="http://en.wikipedia.org/wiki/%C3%85land_Islands">&Aring;land Islands</a> */
    AX("Åland Islands", "ALA", 248, "올란드"),

    /** <a href="http://en.wikipedia.org/wiki/Azerbaijan">Azerbaijan</a> */
    AZ("Azerbaijan", "AZE", 31, "아제르바이잔"),

    /** <a href="http://en.wikipedia.org/wiki/Bosnia_and_Herzegovina">Bosnia and Herzegovina</a> */
    BA("Bosnia and Herzegovina", "BIH", 70, "보스니아 헤르체고비나"),

    /** <a href="http://en.wikipedia.org/wiki/Barbados">Barbados</a> */
    BB("Barbados", "BRB", 52, "바베이도스"),

    /** <a href="http://en.wikipedia.org/wiki/Bangladesh">Bangladesh</a> */
    BD("Bangladesh", "BGD", 50, "방글라데시"),

    /** <a href="http://en.wikipedia.org/wiki/Belgium">Belgium</a> */
    BE("Belgium", "BEL", 56, "벨기에"),

    /** <a href="http://en.wikipedia.org/wiki/Burkina_Faso">Burkina Faso</a> */
    BF("Burkina Faso", "BFA", 854, "부르키나파소"),

    /** <a href="http://en.wikipedia.org/wiki/Bulgaria">Bulgaria</a> */
    BG("Bulgaria", "BGR", 100, "불가리아"),

    /** <a href="http://en.wikipedia.org/wiki/Bahrain">Bahrain</a> */
    BH("Bahrain", "BHR", 48, "바레인"),

    /** <a href="http://en.wikipedia.org/wiki/Burundi">Burundi</a> */
    BI("Burundi", "BDI", 108, "부룬디"),

    /** <a href="http://en.wikipedia.org/wiki/Benin">Benin</a> */
    BJ("Benin", "BEN", 204, "베냉"),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Barth%C3%A9lemy">Saint Barth&eacute;lemy</a> */
    BL("Saint Barth\u00E9lemy", "BLM", 652, "생바르텔레미"),

    /** <a href="http://en.wikipedia.org/wiki/Bermuda">Bermuda</a> */
    BM("Bermuda", "BMU", 60, "버뮤다"),

    /** <a href="http://en.wikipedia.org/wiki/Brunei">Brunei Darussalam</a> */
    BN("Brunei Darussalam", "BRN", 96, "브루나이"),

    /** <a href="http://en.wikipedia.org/wiki/Bolivia">Plurinational State of Bolivia</a> */
    BO("Plurinational State of Bolivia", "BOL", 68, "볼리비아"),

    /** <a href="http://en.wikipedia.org/wiki/Caribbean_Netherlands">Bonaire, Sint Eustatius and Saba</a> */
    BQ("Bonaire, Sint Eustatius and Saba", "BES", 535, "보네르, 신트 유스타티우스 및 사바"),

    /** <a href="http://en.wikipedia.org/wiki/Brazil">Brazil</a> */
    BR("Brazil", "BRA", 76, "브라질"),

    /** <a href="http://en.wikipedia.org/wiki/The_Bahamas">Bahamas</a> */
    BS("Bahamas", "BHS", 44, "바하마"),

    /** <a href="http://en.wikipedia.org/wiki/Bhutan">Bhutan</a> */
    BT("Bhutan", "BTN", 64, "부탄"),

    /** <a href="http://en.wikipedia.org/wiki/Bouvet_Island">Bouvet Island</a> */
    BV("Bouvet Island", "BVT", 74, "부베 섬"),

    /** <a href="http://en.wikipedia.org/wiki/Botswana">Botswana</a> */
    BW("Botswana", "BWA", 72, "보츠와나"),

    /** <a href="http://en.wikipedia.org/wiki/Belarus">Belarus</a> */
    BY("Belarus", "BLR", 112, "벨라루스"),

    /** <a href="http://en.wikipedia.org/wiki/Belize">Belize</a> */
    BZ("Belize", "BLZ", 84, "벨리즈"),

    /** <a href="http://en.wikipedia.org/wiki/Canada">Canada</a> */
    CA("Canada", "CAN", 124, "캐나다"),

    /** <a href="http://en.wikipedia.org/wiki/Cocos_(Keeling)_Islands">Cocos (Keeling) Islands</a> */
    CC("Cocos Islands", "CCK", 166, "코코스 (킬링) 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Democratic_Republic_of_the_Congo">The Democratic Republic of the Congo</a> */
    CD("The Democratic Republic of the Congo", "COD", 180, "콩고 민주 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Central_African_Republic">Central African Republic</a> */
    CF("Central African Republic", "CAF", 140, "중앙 아프리카 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Republic_of_the_Congo">Congo</a> */
    CG("Congo", "COG", 178, "콩고 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Switzerland">Switzerland</a> */
    CH("Switzerland", "CHE", 756, "스위스"),

    /** <a href="http://en.wikipedia.org/wiki/C%C3%B4te_d%27Ivoire">C&ocirc;te d'Ivoire</a> */
    CI("C\u00F4te d'Ivoire", "CIV", 384, "코트디부아르"),

    /** <a href="http://en.wikipedia.org/wiki/Cook_Islands">Cook Islands</a> */
    CK("Cook Islands", "COK", 184, "쿡 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Chile">Chile</a> */
    CL("Chile", "CHL", 152, "칠레"),

    /** <a href="http://en.wikipedia.org/wiki/Cameroon">Cameroon</a> */
    CM("Cameroon", "CMR", 120, "카메룬"),

    /** <a href="http://en.wikipedia.org/wiki/China">China</a> */
    CN("China", "CHN", 156, "중국"),

    /** <a href="http://en.wikipedia.org/wiki/Colombia">Colombia</a> */
    CO("Colombia", "COL", 170, "콜롬비아"),

    /** <a href="http://en.wikipedia.org/wiki/Costa_Rica">Costa Rica</a> */
    CR("Costa Rica", "CRI", 188, "코스타리카"),

    /** <a href="http://en.wikipedia.org/wiki/Cuba">Cuba</a> */
    CU("Cuba", "CUB", 192, "쿠바"),

    /** <a href="http://en.wikipedia.org/wiki/Cape_Verde">Cape Verde</a> */
    CV("Cape Verde", "CPV", 132, "카보베르데"),

    /** <a href="http://en.wikipedia.org/wiki/Cura%C3%A7ao">Cura&ccedil;ao</a> */
    CW("Cura\u00E7ao", "CUW", 531, "쿠라카오"),

    /** <a href="http://en.wikipedia.org/wiki/Christmas_Island">Christmas Island</a> */
    CX("Christmas Island", "CXR", 162, "크리스마스 섬"),

    /** <a href="http://en.wikipedia.org/wiki/Cyprus">Cyprus</a> */
    CY("Cyprus", "CYP", 196, "키프로스"),

    /** <a href="http://en.wikipedia.org/wiki/Czech_Republic">Czech Republic</a> */
    CZ("Czech Republic", "CZE", 203, "체코"),

    /** <a href="http://en.wikipedia.org/wiki/Germany">Germany</a> */
    DE("Germany", "DEU", 276, "독일"),

    /** <a href="http://en.wikipedia.org/wiki/Djibouti">Djibouti </a> */
    DJ("Djibouti", "DJI", 262, "지부티"),

    /** <a href="http://en.wikipedia.org/wiki/Denmark">Denmark</a> */
    DK("Denmark", "DNK", 208, "덴마크"),

    /** <a href="http://en.wikipedia.org/wiki/Dominica">Dominica</a> */
    DM("Dominica", "DMA", 212, "도미니카"),

    /** <a href="http://en.wikipedia.org/wiki/Dominican_Republic">Dominican Republic</a> */
    DO("Dominican Republic", "DOM", 214, "도미니카 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Algeria">Algeria</a> */
    DZ("Algeria", "DZA", 12, "알제리"),

    /** <a href="http://en.wikipedia.org/wiki/Ecuador">Ecuador</a> */
    EC("Ecuador", "ECU", 218, "에콰도르"),

    /** <a href="http://en.wikipedia.org/wiki/Estonia">Estonia</a> */
    EE("Estonia", "EST", 233, "에스토니아"),

    /** <a href="http://en.wikipedia.org/wiki/Egypt">Egypt</a> */
    EG("Egypt", "EGY", 818, "이집트"),

    /** <a href="http://en.wikipedia.org/wiki/Western_Sahara">Western Sahara</a> */
    EH("Western Sahara", "ESH", 732, "서사하라"),

    /** <a href="http://en.wikipedia.org/wiki/Eritrea">Eritrea</a> */
    ER("Eritrea", "ERI", 232, "에리트레아"),

    /** <a href="http://en.wikipedia.org/wiki/Spain">Spain</a> */
    ES("Spain", "ESP", 724, "스페인"),

    /** <a href="http://en.wikipedia.org/wiki/Ethiopia">Ethiopia</a> */
    ET("Ethiopia", "ETH", 231, "에티오피아"),

    /** <a href="http://en.wikipedia.org/wiki/Finland">Finland</a> */
    FI("Finland", "FIN", 246, "핀란드"),

    /** <a href="http://en.wikipedia.org/wiki/Fiji">Fiji</a> */
    FJ("Fiji", "FJI", 242, "피지"),

    /** <a href="http://en.wikipedia.org/wiki/Falkland_Islands">Falkland Islands (Malvinas)</a> */
    FK("Falkland Islands", "FLK", 238, "포클랜드 (말비나스) 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Federated_States_of_Micronesia">Federated States of Micronesia</a> */
    FM("Federated States of Micronesia", "FSM", 583, "미크로네시아 연방"),

    /** <a href="http://en.wikipedia.org/wiki/Faroe_Islands">Faroe Islands</a> */
    FO("Faroe Islands", "FRO", 234, "페로 제도"),

    /** <a href="http://en.wikipedia.org/wiki/France">France</a> */
    FR("France", "FRA", 250, "프랑스"),

    /** <a href="http://en.wikipedia.org/wiki/Gabon">Gabon </a> */
    GA("Gabon", "GAB", 266, "가봉"),

    /** <a href="http://en.wikipedia.org/wiki/United_Kingdom">United Kingdom</a> */
    GB("United Kingdom", "GBR", 826, "영국"),

    /** <a href="http://en.wikipedia.org/wiki/Grenada">Grenada</a> */
    GD("Grenada", "GRD", 308, "그레나다"),

    /** <a href="http://en.wikipedia.org/wiki/Georgia_(country)">Georgia</a> */
    GE("Georgia", "GEO", 268, "조지아"),

    /** <a href="http://en.wikipedia.org/wiki/French_Guiana">French Guiana</a> */
    GF("French Guiana", "GUF", 254, "프랑스령 기아나"),

    /** <a href="http://en.wikipedia.org/wiki/Guernsey">Guemsey</a> */
    GG("Guemsey", "GGY", 831, "건지"),

    /** <a href="http://en.wikipedia.org/wiki/Ghana">Ghana</a> */
    GH("Ghana", "GHA", 288, "가나"),

    /** <a href="http://en.wikipedia.org/wiki/Gibraltar">Gibraltar</a> */
    GI("Gibraltar", "GIB", 292, "지브롤터"),

    /** <a href="http://en.wikipedia.org/wiki/Greenland">Greenland</a> */
    GL("Greenland", "GRL", 304, "그린란드"),

    /** <a href="http://en.wikipedia.org/wiki/The_Gambia">Gambia</a> */
    GM("Gambia", "GMB", 270, "감비아"),

    /** <a href="http://en.wikipedia.org/wiki/Guinea">Guinea</a> */
    GN("Guinea", "GIN", 324, "기니"),

    /** <a href="http://en.wikipedia.org/wiki/Guadeloupe">Guadeloupe</a> */
    GP("Guadeloupe", "GLP", 312, "과들루프"),

    /** <a href="http://en.wikipedia.org/wiki/Equatorial_Guinea">Equatorial Guinea</a> */
    GQ("Equatorial Guinea", "GNQ", 226, "적도 기니"),

    /** <a href="http://en.wikipedia.org/wiki/Greece">Greece</a> */
    GR("Greece", "GRC", 300, "그리스"),

    /** <a href="http://en.wikipedia.org/wiki/South_Georgia_and_the_South_Sandwich_Islands">South Georgia and the South Sandwich Islands</a> */
    GS("South Georgia and the South Sandwich Islands", "SGS", 239, "남조지아 및 사우스 샌드위치 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Guatemala">Guatemala</a> */
    GT("Guatemala", "GTM", 320, "과테말라"),

    /** <a href="http://en.wikipedia.org/wiki/Guam">Guam</a> */
    GU("Guam", "GUM", 316, "괌"),

    /** <a href="http://en.wikipedia.org/wiki/Guinea-Bissau">Guinea-Bissau</a> */
    GW("Guinea-Bissau", "GNB", 624, "기니비사우"),

    /** <a href="http://en.wikipedia.org/wiki/Guyana">Guyana</a> */
    GY("Guyana", "GUY", 328, "가이아나"),

    /** <a href="http://en.wikipedia.org/wiki/Hong_Kong">Hong Kong</a> */
    HK("Hong Kong", "HKG", 344, "홍콩"),

    /** <a href="http://en.wikipedia.org/wiki/Heard_Island_and_McDonald_Islands">Heard Island and McDonald Islands</a> */
    HM("Heard Island and McDonald Islands", "HMD", 334, "허드 섬 및 맥도널드 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Honduras">Honduras</a> */
    HN("Honduras", "HND", 340, "온두라스"),

    /** <a href="http://en.wikipedia.org/wiki/Croatia">Croatia</a> */
    HR("Croatia", "HRV", 191, "크로아티아"),

    /** <a href="http://en.wikipedia.org/wiki/Haiti">Haiti</a> */
    HT("Haiti", "HTI", 332, "아이티"),

    /** <a href="http://en.wikipedia.org/wiki/Hungary">Hungary</a> */
    HU("Hungary", "HUN", 348, "헝가리"),

    /** <a href="http://en.wikipedia.org/wiki/Indonesia">Indonesia</a> */
    ID("Indonesia", "IDN", 360, "인도네시아"),

    /** <a href="http://en.wikipedia.org/wiki/Republic_of_Ireland">Ireland</a> */
    IE("Ireland", "IRL", 372, "아일랜드"),

    /** <a href="http://en.wikipedia.org/wiki/Israel">Israel</a> */
    IL("Israel", "ISR", 376, "이스라엘"),

    /** <a href="http://en.wikipedia.org/wiki/Isle_of_Man">Isle of Man</a> */
    IM("Isle of Man", "IMN", 833, "맨 섬"),

    /** <a href="http://en.wikipedia.org/wiki/India">India</a> */
    IN("India", "IND", 356, "인도"),

    /** <a href="http://en.wikipedia.org/wiki/British_Indian_Ocean_Territory">British Indian Ocean Territory</a> */
    IO("British Indian Ocean Territory", "IOT", 86, "영국령 인도양 식민지"),

    /** <a href="http://en.wikipedia.org/wiki/Iraq">Iraq</a> */
    IQ("Iraq", "IRQ", 368, "이라크"),

    /** <a href="http://en.wikipedia.org/wiki/Iran">Islamic Republic of Iran</a> */
    IR("Islamic Republic of Iran", "IRN", 364, "이란"),

    /** <a href="http://en.wikipedia.org/wiki/Iceland">Iceland</a> */
    IS("Iceland", "ISL", 352, "아이슬란드"),

    /** <a href="http://en.wikipedia.org/wiki/Italy">Italy</a> */
    IT("Italy", "ITA", 380, "이탈리아"),

    /** <a href="http://en.wikipedia.org/wiki/Jersey">Jersey</a> */
    JE("Jersey", "JEY", 832, "저지"),

    /** <a href="http://en.wikipedia.org/wiki/Jamaica">Jamaica</a> */
    JM("Jamaica", "JAM", 388, "자메이카"),

    /** <a href="http://en.wikipedia.org/wiki/Jordan">Jordan</a> */
    JO("Jordan", "JOR", 400, "요르단"),

    /** <a href="http://en.wikipedia.org/wiki/Japan">Japan</a> */
    JP("Japan", "JPN", 392, "일본"),

    /** <a href="http://en.wikipedia.org/wiki/Kenya">Kenya</a> */
    KE("Kenya", "KEN", 404, "케냐"),

    /** <a href="http://en.wikipedia.org/wiki/Kyrgyzstan">Kyrgyzstan</a> */
    KG("Kyrgyzstan", "KGZ", 417, "키르기스스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Cambodia">Cambodia</a> */
    KH("Cambodia", "KHM", 116, "캄보디아"),

    /** <a href="http://en.wikipedia.org/wiki/Kiribati">Kiribati</a> */
    KI("Kiribati", "KIR", 296, "키리바시"),

    /** <a href="http://en.wikipedia.org/wiki/Comoros">Comoros</a> */
    KM("Comoros", "COM", 174, "코모로"),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Kitts_and_Nevis">Saint Kitts and Nevis</a> */
    KN("Saint Kitts and Nevis", "KNA", 659, "세인트키츠 네비스"),

    /** <a href="http://en.wikipedia.org/wiki/North_Korea">Democratic People's Republic of Korea</a> */
    KP("Democratic People's Republic of Korea", "PRK", 408, "조선 민주주의 인민 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/South_Korea">Republic of Korea</a> */
    KR("Republic of Korea", "KOR", 410, "대한민국"),

    /** <a href="http://en.wikipedia.org/wiki/Kuwait">Kuwait</a> */
    KW("Kuwait", "KWT", 414, "쿠웨이트"),

    /** <a href="http://en.wikipedia.org/wiki/Cayman_Islands">Cayman Islands</a> */
    KY("Cayman Islands", "CYM", 136, "케이맨 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Kazakhstan">Kazakhstan</a> */
    KZ("Kazakhstan", "KAZ", 398, "카자흐스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Laos">Lao People's Democratic Republic</a> */
    LA("Lao People's Democratic Republic", "LAO", 418, "라오스 인민 민주 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Lebanon">Lebanon</a> */
    LB("Lebanon", "LBN", 422, "레바논"),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Lucia">Saint Lucia</a> */
    LC("Saint Lucia", "LCA", 662, "세인트루시아"),

    /** <a href="http://en.wikipedia.org/wiki/Liechtenstein">Liechtenstein</a> */
    LI("Liechtenstein", "LIE", 438, "리히텐슈타인"),

    /** <a href="http://en.wikipedia.org/wiki/Sri_Lanka">Sri Lanka</a> */
    LK("Sri Lanka", "LKA", 144, "스리랑카"),

    /** <a href="http://en.wikipedia.org/wiki/Liberia">Liberia</a> */
    LR("Liberia", "LBR", 430, "라이베리아"),

    /** <a href="http://en.wikipedia.org/wiki/Lesotho">Lesotho</a> */
    LS("Lesotho", "LSO", 426, "레소토"),

    /** <a href="http://en.wikipedia.org/wiki/Lithuania">Lithuania</a> */
    LT("Lithuania", "LTU", 440, "리투아니아"),

    /** <a href="http://en.wikipedia.org/wiki/Luxembourg">Luxembourg</a> */
    LU("Luxembourg", "LUX", 442, "룩셈부르크"),

    /** <a href="http://en.wikipedia.org/wiki/Latvia">Latvia</a> */
    LV("Latvia", "LVA", 428, "라트비아"),

    /** <a href="http://en.wikipedia.org/wiki/Libya">Libya</a> */
    LY("Libya", "LBY", 434, "리비아"),

    /** <a href="http://en.wikipedia.org/wiki/Morocco">Morocco</a> */
    MA("Morocco", "MAR", 504, "모로코"),

    /** <a href="http://en.wikipedia.org/wiki/Monaco">Monaco</a> */
    MC("Monaco", "MCO", 492, "모나코"),

    /** <a href="http://en.wikipedia.org/wiki/Moldova">Republic of Moldova</a> */
    MD("Republic of Moldova", "MDA", 498, "몰도바 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Montenegro">Montenegro</a> */
    ME("Montenegro", "MNE", 499, "몬테네그로"),

    /** <a href="http://en.wikipedia.org/wiki/Collectivity_of_Saint_Martin">Saint Martin (French part)</a> */
    MF("Saint Martin", "MAF", 663, "생마르탱"),

    /** <a href="http://en.wikipedia.org/wiki/Madagascar">Madagascar</a> */
    MG("Madagascar", "MDG", 450, "마다가스카르"),

    /** <a href="http://en.wikipedia.org/wiki/Marshall_Islands">Marshall Islands</a> */
    MH("Marshall Islands", "MHL", 584, "마셜 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Republic_of_Macedonia">The former Yugoslav Republic of Macedonia</a> */
    MK("The former Yugoslav Republic of Macedonia", "MKD", 807, "구 유고슬라비아 공화국 마케도니아"),

    /** <a href="http://en.wikipedia.org/wiki/Mali">Mali</a> */
    ML("Mali", "MLI", 466, "말리"),

    /** <a href="http://en.wikipedia.org/wiki/Myanmar">Myanmar</a> */
    MM("Myanmar", "MMR", 104, "미얀마"),

    /** <a href="http://en.wikipedia.org/wiki/Mongolia">Mongolia</a> */
    MN("Mongolia", "MNG", 496, "몽골"),

    /** <a href="http://en.wikipedia.org/wiki/Macau">Macao</a> */
    MO("Macao", "MAC", 446, "마카오"),

    /** <a href="http://en.wikipedia.org/wiki/Northern_Mariana_Islands">Northern Mariana Islands</a> */
    MP("Northern Mariana Islands", "MNP",580, "북마리아나 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Martinique">Martinique</a> */
    MQ("Martinique", "MTQ", 474, "마르티니크"),

    /** <a href="http://en.wikipedia.org/wiki/Mauritania">Mauritania</a> */
    MR("Mauritania", "MRT", 478, "모리타니아"),

    /** <a href="http://en.wikipedia.org/wiki/Montserrat">Montserrat</a> */
    MS("Montserrat", "MSR", 500, "몬트세라트"),

    /** <a href="http://en.wikipedia.org/wiki/Malta">Malta</a> */
    MT("Malta", "MLT", 470, "몰타"),

    /** <a href="http://en.wikipedia.org/wiki/Mauritius">Mauritius</a> */
    MU("Mauritius", "MUS", 480, "모리셔스"),

    /** <a href="http://en.wikipedia.org/wiki/Maldives">Maldives</a> */
    MV("Maldives", "MDV", 462, "몰디브"),

    /** <a href="http://en.wikipedia.org/wiki/Malawi">Malawi</a> */
    MW("Malawi", "MWI", 454, "말라위"),

    /** <a href="http://en.wikipedia.org/wiki/Mexico">Mexico</a> */
    MX("Mexico", "MEX", 484, "멕시코"),

    /** <a href="http://en.wikipedia.org/wiki/Malaysia">Malaysia</a> */
    MY("Malaysia", "MYS", 458, "말레이시아"),

    /** <a href="http://en.wikipedia.org/wiki/Mozambique">Mozambique</a> */
    MZ("Mozambique", "MOZ", 508, "모잠비크"),

    /** <a href="http://en.wikipedia.org/wiki/Namibia">Namibia</a> */
    NA("Namibia", "NAM", 516, "나미비아"),

    /** <a href="http://en.wikipedia.org/wiki/New_Caledonia">New Caledonia</a> */
    NC("New Caledonia", "NCL", 540, "뉴칼레도니아"),

    /** <a href="http://en.wikipedia.org/wiki/Niger">Niger</a> */
    NE("Niger", "NER", 562, "니제르"),

    /** <a href="http://en.wikipedia.org/wiki/Norfolk_Island">Norfolk Island</a> */
    NF("Norfolk Island", "NFK", 574, "노퍽 섬"),

    /** <a href="http://en.wikipedia.org/wiki/Nigeria">Nigeria</a> */
    NG("Nigeria","NGA", 566, "나이지리아"),

    /** <a href="http://en.wikipedia.org/wiki/Nicaragua">Nicaragua</a> */
    NI("Nicaragua", "NIC", 558, "니카라과"),

    /** <a href="http://en.wikipedia.org/wiki/Netherlands">Netherlands</a> */
    NL("Netherlands", "NLD", 528, "네덜란드"),

    /** <a href="http://en.wikipedia.org/wiki/Norway">Norway</a> */
    NO("Norway", "NOR", 578, "노르웨이"),

    /** <a href="http://en.wikipedia.org/wiki/Nepal">Nepal</a> */
    NP("Nepal", "NPL", 524, "네팔"),

    /** <a href="http://en.wikipedia.org/wiki/Nauru">Nauru</a> */
    NR("Nauru", "NRU", 520, "나우루"),

    /** <a href="http://en.wikipedia.org/wiki/Niue">Niue</a> */
    NU("Niue", "NIU", 570, "니우에"),

    /** <a href="http://en.wikipedia.org/wiki/New_Zealand">New Zealand</a> */
    NZ("New Zealand", "NZL", 554, "뉴질랜드"),

    /** <a href=http://en.wikipedia.org/wiki/Oman"">Oman</a> */
    OM("Oman", "OMN", 512, "오만"),

    /** <a href="http://en.wikipedia.org/wiki/Panama">Panama</a> */
    PA("Panama", "PAN", 591, "파나마"),

    /** <a href="http://en.wikipedia.org/wiki/Peru">Peru</a> */
    PE("Peru", "PER", 604, "페루"),

    /** <a href="http://en.wikipedia.org/wiki/French_Polynesia">French Polynesia</a> */
    PF("French Polynesia", "PYF", 258, "프랑스령 폴리네시아"),

    /** <a href="http://en.wikipedia.org/wiki/Papua_New_Guinea">Papua New Guinea</a> */
    PG("Papua New Guinea", "PNG", 598, "파푸아뉴기니"),

    /** <a href="http://en.wikipedia.org/wiki/Philippines">Philippines</a> */
    PH("Philippines", "PHL", 608, "필리핀"),

    /** <a href="http://en.wikipedia.org/wiki/Pakistan">Pakistan</a> */
    PK("Pakistan", "PAK", 586, "파키스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Poland">Poland</a> */
    PL("Poland", "POL", 616, "폴란드"),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Pierre_and_Miquelon">Saint Pierre and Miquelon</a> */
    PM("Saint Pierre and Miquelon", "SPM", 666, "생피에르 미클롱"),

    /** <a href="http://en.wikipedia.org/wiki/Pitcairn_Islands">Pitcairn</a> */
    PN("Pitcairn", "PCN", 612, "핏케언 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Puerto_Rico">Puerto Rico</a> */
    PR("Puerto Rico", "PRI", 630, "푸에르토리코"),

    /** <a href="http://en.wikipedia.org/wiki/Palestinian_territories">Occupied Palestinian Territory</a> */
    PS("Occupied Palestinian Territory", "PSE", 275, "팔레스타인 점령지"),

    /** <a href="http://en.wikipedia.org/wiki/Portugal">Portugal</a> */
    PT("Portugal", "PRT", 620, "포르투갈"),

    /** <a href="http://en.wikipedia.org/wiki/Palau">Palau</a> */
    PW("Palau", "PLW", 585, "팔라우"),

    /** <a href="http://en.wikipedia.org/wiki/Paraguay">Paraguay</a> */
    PY("Paraguay", "PRY", 600, "파라과이"),

    /** <a href="http://en.wikipedia.org/wiki/Qatar">Qatar</a> */
    QA("Qatar", "QAT", 634, "카타르"),

    /** <a href="http://en.wikipedia.org/wiki/R%C3%A9union">R&eacute;union</a> */
    RE("R\u00E9union", "REU", 638, "레위니옹"),

    /** <a href="http://en.wikipedia.org/wiki/Romania">Romania</a> */
    RO("Romania", "ROU", 642, "루마니아"),

    /** <a href="http://en.wikipedia.org/wiki/Serbia">Serbia</a> */
    RS("Serbia", "SRB", 688, "세르비아"),

    /** <a href="http://en.wikipedia.org/wiki/Russia">Russian Federation</a> */
    RU("Russian Federation", "RUS", 643, "러시아 연방"),

    /** <a href="http://en.wikipedia.org/wiki/Rwanda">Rwanda</a> */
    RW("Rwanda", "RWA", 646, "르완다"),

    /** <a href="http://en.wikipedia.org/wiki/Saudi_Arabia">Saudi Arabia</a> */
    SA("Saudi Arabia", "SAU", 682, "사우디아라비아"),

    /** <a href="http://en.wikipedia.org/wiki/Solomon_Islands">Solomon Islands</a> */
    SB("Solomon Islands", "SLB", 90, "솔로몬 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Seychelles">Seychelles</a> */
    SC("Seychelles", "SYC", 690, "세이셸"),

    /** <a href="http://en.wikipedia.org/wiki/Sudan">Sudan</a> */
    SD("Sudan", "SDN", 729, "수단"),

    /** <a href="http://en.wikipedia.org/wiki/Sweden">Sweden</a> */
    SE("Sweden", "SWE", 752, "스웨덴"),

    /** <a href="http://en.wikipedia.org/wiki/Singapore">Singapore</a> */
    SG("Singapore", "SGP", 702, "싱가포르"),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Helena,_Ascension_and_Tristan_da_Cunha">Saint Helena, Ascension and Tristan da Cunha</a> */
    SH("Saint Helena, Ascension and Tristan da Cunha", "SHN", 654, "세인트헬레나, 어센션 및 트리스탄 다 쿠냐"),

    /** <a href="http://en.wikipedia.org/wiki/Slovenia">Slovenia</a> */
    SI("Slovenia", "SVN", 705, "슬로베니아"),

    /** <a href="http://en.wikipedia.org/wiki/Svalbard_and_Jan_Mayen">Svalbard and Jan Mayen</a> */
    SJ("Svalbard and Jan Mayen", "SJM", 744, "스발바르드 및 얀 마웬"),

    /** <a href="http://en.wikipedia.org/wiki/Slovakia">Slovakia</a> */
    SK("Slovakia", "SVK", 703, "슬로바키아"),

    /** <a href="http://en.wikipedia.org/wiki/Sierra_Leone">Sierra Leone</a> */
    SL("Sierra Leone", "SLE", 694, "시에라리온"),

    /** <a href="http://en.wikipedia.org/wiki/San_Marino">San Marino</a> */
    SM("San Marino", "SMR", 674, "산마리노"),

    /** <a href="http://en.wikipedia.org/wiki/Senegal">Senegal</a> */
    SN("Senegal", "SEN", 686, "세네갈"),

    /** <a href="http://en.wikipedia.org/wiki/Somalia">Somalia</a> */
    SO("Somalia", "SOM", 706, "소말리아"),

    /** <a href="http://en.wikipedia.org/wiki/Suriname">Suriname</a> */
    SR("Suriname", "SUR", 740, "수리남"),

    /** <a href="http://en.wikipedia.org/wiki/South_Sudan">South Sudan</a> */
    SS("South Sudan", "SSD", 728, "남수단"),

    /** <a href="http://en.wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe">Sao Tome and Principe</a> */
    ST("Sao Tome and Principe", "STP", 678, "상투메 프린시페"),

    /** <a href="http://en.wikipedia.org/wiki/El_Salvador">El Salvador</a> */
    SV("El Salvador", "SLV", 222, "엘살바도르"),

    /** <a href="http://en.wikipedia.org/wiki/Sint_Maarten">Sint Maarten (Dutch part)</a> */
    SX("Sint Maarten", "SXM", 534, "신트 마르턴"),

    /** <a href="http://en.wikipedia.org/wiki/Syria">Syrian Arab Republic</a> */
    SY("Syrian Arab Republic", "SYR", 760, "시리아 아랍 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Swaziland">Swaziland</a> */
    SZ("Swaziland", "SWZ", 748, "스와질란드"),

    /** <a href="http://en.wikipedia.org/wiki/Turks_and_Caicos_Islands">Turks and Caicos Islands</a> */
    TC("Turks and Caicos Islands", "TCA", 796, "터크스 케이커스 제도"),

    /** <a href="http://en.wikipedia.org/wiki/Chad">Chad</a> */
    TD("Chad", "TCD", 148, "차드"),

    /** <a href="http://en.wikipedia.org/wiki/French_Southern_and_Antarctic_Lands">French Southern Territories</a> */
    TF("French Southern Territories", "ATF", 260, "프랑스령 남부 및 남극 지역"),

    /** <a href="http://en.wikipedia.org/wiki/Togo">Togo</a> */
    TG("Togo", "TGO", 768, "토고"),

    /** <a href="http://en.wikipedia.org/wiki/Thailand">Thailand</a> */
    TH("Thailand", "THA", 764, "태국"),

    /** <a href="http://en.wikipedia.org/wiki/Tajikistan">Tajikistan</a> */
    TJ("Tajikistan", "TJK", 762, "타지키스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Tokelau">Tokelau</a> */
    TK("Tokelau", "TKL", 772, "토켈라우"),

    /** <a href="http://en.wikipedia.org/wiki/East_Timor">Timor-Leste</a> */
    TL("Timor-Leste", "TLS", 626, "동티모르"),

    /** <a href="http://en.wikipedia.org/wiki/Turkmenistan">Turkmenistan</a> */
    TM("Turkmenistan", "TKM", 795, "투르크메니스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Tunisia">Tunisia</a> */
    TN("Tunisia", "TUN", 788, "튀니지"),

    /** <a href="http://en.wikipedia.org/wiki/Tonga">Tonga</a> */
    TO("Tonga", "TON", 776, "통가"),

    /** <a href="http://en.wikipedia.org/wiki/Turkey">Turkey</a> */
    TR("Turkey", "TUR", 792, "터키"),

    /** <a href="http://en.wikipedia.org/wiki/Trinidad_and_Tobago">Trinidad and Tobago</a> */
    TT("Trinidad and Tobago", "TTO", 780, "트리니다드 토바고"),

    /** <a href="http://en.wikipedia.org/wiki/Tuvalu">Tuvalu</a> */
    TV("Tuvalu", "TUV", 798, "투발루"),

    /** <a href="http://en.wikipedia.org/wiki/Taiwan">Taiwan, Province of China</a> */
    TW("Taiwan, Province of China", "TWN", 158, "대만"),

    /** <a href="http://en.wikipedia.org/wiki/Tanzania">United Republic of Tanzania</a> */
    TZ("United Republic of Tanzania", "TZA", 834, "탄자니아 연방 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Ukraine">Ukraine</a> */
    UA("Ukraine", "UKR", 804, "우크라이나"),

    /** <a href="http://en.wikipedia.org/wiki/Uganda">Uganda</a> */
    UG("Uganda", "UGA", 800, "우간다"),

    /** <a href="http://en.wikipedia.org/wiki/United_States_Minor_Outlying_Islands">United States Minor Outlying Islands</a> */
    UM("United States Minor Outlying Islands", "UMI", 581, "미국령 군소 제도"),

    /** <a href="http://en.wikipedia.org/wiki/United_States">United States</a> */
    US("United States", "USA", 840, "미국"),

    /** <a href="http://en.wikipedia.org/wiki/Uruguay">Uruguay</a> */
    UY("Uruguay", "URY", 858, "우루과이"),

    /** <a href="http://en.wikipedia.org/wiki/Uzbekistan">Uzbekistan</a> */
    UZ("Uzbekistan", "UZB", 860, "우즈베키스탄"),

    /** <a href="http://en.wikipedia.org/wiki/Vatican_City">Holy See (Vatican City State)</a> */
    VA("Holy See", "VAT", 336, "바티칸 시국"),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Vincent_and_the_Grenadines">Saint Vincent and the Grenadines</a> */
    VC("Saint Vincent and the Grenadines", "VCT", 670, "세인트빈센트 그레나딘"),

    /** <a href="http://en.wikipedia.org/wiki/Venezuela">Bolivarian Republic of Venezuela</a> */
    VE("Bolivarian Republic of Venezuela", "VEN", 862, "베네수엘라 볼리바르 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/British_Virgin_Islands">British Virgin Islands</a> */
    VG("British Virgin Islands", "VGB", 92, "영국령 버진 아일랜드"),

    /** <a href="http://en.wikipedia.org/wiki/United_States_Virgin_Islands">Virgin Islands, U.S.</a> */
    VI("Virgin Islands, U.S.", "VIR", 850, "미국령 버진 아일랜드"),

    /** <a href="http://en.wikipedia.org/wiki/Vietnam">Viet Nam</a> */
    VN("Viet Nam", "VNM", 704, "베트남"),

    /** <a href="http://en.wikipedia.org/wiki/Vanuatu">Vanuatu</a> */
    VU("Vanuatu", "VUT", 548, "바누아투"),

    /** <a href="http://en.wikipedia.org/wiki/Wallis_and_Futuna">Wallis and Futuna</a> */
    WF("Wallis and Futuna", "WLF", 876, "왈리스 푸투나"),

    /** <a href="http://en.wikipedia.org/wiki/Samoa">Samoa</a> */
    WS("Samoa", "WSM", 882, "사모아"),

    /** <a href="http://en.wikipedia.org/wiki/Yemen">Yemen</a> */
    YE("Yemen", "YEM", 887, "예멘"),

    /** <a href="http://en.wikipedia.org/wiki/Mayotte">Mayotte</a> */
    YT("Mayotte", "MYT", 175, "마요트"),

    /** <a href="http://en.wikipedia.org/wiki/South_Africa">South Africa</a> */
    ZA("South Africa", "ZAF", 710, "남아프리카 공화국"),

    /** <a href="http://en.wikipedia.org/wiki/Zambia">Zambia</a> */
    ZM("Zambia", "ZMB", 894, "잠비아"),

    /** <a href="http://en.wikipedia.org/wiki/Zimbabwe">Zimbabwe</a> */
    ZW("Zimbabwe", "ZWE", 716, "짐바브웨"),
    ;
    // @formatter:on


    private static final Map<String, Country> alpha3Map = new HashMap<String, Country>();
    private static final Map<Integer, Country> numericMap = new HashMap<Integer, Country>();


    static
    {
        for (Country cc : values())
        {
            alpha3Map.put(cc.getAlpha3(), cc);
            numericMap.put(cc.getNumeric(), cc);
        }
    }


    private final String name;
    private final String alpha3;
    private final int numeric;
    private final String koreanName; // 새로운 한글명 필드 추가


    private Country(String name, String alpha3, int numeric, String koreanName)
    {
        this.name = name;
        this.alpha3 = alpha3;
        this.numeric = numeric;
        this.koreanName = koreanName;
    }


    /**
     * Get the country name.
     *
     * @return
     *         The country name.
     */
    public String getName()
    {
        return name;
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     * >ISO 3166-1 alpha-2</a> code.
     *
     * @return
     *         The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     *         >ISO 3166-1 alpha-2</a> code.
     */
    public String getAlpha2()
    {
        return name();
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     * >ISO 3166-1 alpha-3</a> code.
     *
     * @return
     *         The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     *         >ISO 3166-1 alpha-3</a> code.
     */
    public String getAlpha3()
    {
        return alpha3;
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     * >ISO 3166-1 numeric</a> code.
     *
     * @return
     *         The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     *         >ISO 3166-1 numeric</a> code.
     */
    public int getNumeric()
    {
        return numeric;
    }

    /**
     * Get the Korean name of the country.
     *
     * @return
     *         The Korean name of the country.
     */
    public String getKoreanName()
    {
        return koreanName;
    }


    /**
     * Get a CountryCode that corresponds to a given ISO 3166-1
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     *
     * @param code
     *         An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     *         >alpha-2</a> or <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     *         >alpha-3</a> code.
     *
     * @return
     *         A CountryCode instance, or null if not found.
     */
    public static Country getByCode(String code)
    {
        if (code == null)
        {
            return null;
        }

        switch (code.length())
        {
            case 2:
                return getByAlpha2Code(code);

            case 3:
                return getByAlpha3Code(code);

            default:
                return null;
        }
    }


    private static Country getByAlpha2Code(String code)
    {
        try
        {
            return Enum.valueOf(Country.class, code);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }


    private static Country getByAlpha3Code(String code)
    {
        return alpha3Map.get(code);
    }


    /**
     * Get a CountryCode that corresponds to a given
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric">ISO 3166-1
     * numeric</a> code.
     *
     * @param code
     *         An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     *         >ISO 3166-1 numeric</a> code.
     *
     * @return
     *         A CountryCode instance, or null if not found.
     */
    public static Country getByCode(int code)
    {
        return numericMap.get(code);
    }
}
