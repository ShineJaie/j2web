<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container" id="jcontent-wrapper" funcModel="home" funcInit="initpage_home" menuSelct="page_home">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">

            <sec:authorize access="isRememberMe()">
                <h1># 用户通过 "Remember Me Cookies" 登录.</h1>
            </sec:authorize>

            <sec:authorize access="isFullyAuthenticated()">
                <h1># 用户通过 username / password 登录.</h1>
            </sec:authorize>

            <h1 class="text-primary">欢迎来到 Shine Jaie 个人网站
                <small class="text-danger">孙子兵法 始计篇</small>
            </h1>

            <p class="article-space text-danger">始计篇</p>

            <p class="article-space text-danger">故经之以五事，校之以计而索其情：一曰道，二曰天，三曰地，四曰将，五曰法。
                道者，令民与上同意也，故可以与之死，可以与之生，而不畏危。天者，阴阳、寒暑、时制也。地者，远近、险易、广狭、死生也。
                将者，智、信、仁、勇、严也。法者，曲制、官道、主用也。凡此五者，将莫不闻，知之者胜，不知者不胜。
                故校之以计而索其情，曰：主孰有道？将孰有能？天地孰得？法令孰行？兵众孰强？士卒孰练？赏罚孰明？吾以此知胜负矣。</p>

            <p class="article-space text-danger">将听吾计，用之必胜，留之；将不听吾计，用之必败，去之。</p>

            <p class="article-space text-danger">计利以听，乃为之势，以佐其外。势者，因利而制权也。兵者，诡道也。故能而示之不能，用而示之不用，近而示之远，远而示之近；
                利而诱之，乱而取之，实而备之，强而避之，怒而挠之，卑而骄之，佚而劳之，亲而离之。攻其无备，出其不意。此兵家之胜，不可先传也。</p>

            <p class="article-space text-danger">
                　夫未战而庙算胜者，得算多也；未战而庙算不胜者，得算少也。多算胜，少算不胜，而况于无算乎！吾以此观之，胜负见矣。</p>
        </div>
    </div>

</div>
