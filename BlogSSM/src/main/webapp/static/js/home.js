layui.use(["jquery", "util"], function() {
	var n = layui.jquery,
		t = layui.util;
	n(window).load(function() {
		n("#loading").fadeOut(500);
		(new WOW).init()
	});
	t.fixbar();
	n(".next").click(function() {
		n("html,body").animate({
			scrollTop: n("#section1").outerHeight() + 1
		}, 600)
	});
	n("#menu").on("click", function() {
		var t = n(this).attr("data-mark");
		t === "false" ? (n(this).removeClass("menu_open").addClass("menu_close"), n("#navgation").removeClass("navgation_close").addClass("navgation_open"), n(this).attr({
			"data-mark": "true"
		})) : (n(this).removeClass("menu_close").addClass("menu_open"), n("#navgation").removeClass("navgation_open").addClass("navgation_close"), n(this).attr({
			"data-mark": "false"
		}))
	})
})