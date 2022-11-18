$(document).ready(function () {
  "use strict";

  //********* Animated Headline

  $(".animate-rotate-3").animatedHeadline({
    animationType: "rotate-3",
  });

  //********** menu background color change while scroll

  $(window).on("scroll", function () {
    var menu_area = $(".nav-area");
    if ($(window).scrollTop() > 200) {
      menu_area.addClass("sticky_navigation");
    } else {
      menu_area.removeClass("sticky_navigation");
    }
  });

  //********** menu hides after click (mobile menu)

  $(document).on("click", ".navbar-collapse.in", function (e) {
    if ($(e.target).is("a") && $(e.target).attr("class") != "dropdown-toggle") {
      $(this).collapse("hide");
    }
  });

  //*********** scrollspy js

  $("body").scrollspy({
    target: ".navbar-collapse",
    offset: 195,
  });

  //************* venobox js

  $(document).ready(function () {
    $(".venobox").venobox();
  });

  //************ smooth scroll js

  $("a.smooth-menu,a.custom-btn").on("click", function (e) {
    e.preventDefault();
    var anchor = $(this);
    $("html, body")
      .stop()
      .animate(
        {
          scrollTop: $(anchor.attr("href")).offset().top - 50,
        },
        1000
      );
  });

  setTimeout(function () {
    $("body").addClass("loaded");
    $("h1").css("color", "#222222");
  }, 3000);

  //************* Owl Carousel

  $(".testimonial-slider").owlCarousel({
    loop: true,
    margin: 30,
    nav: false,
    dots: true,
    autoplay: true,
    autoplayHoverPause: true,
    responsive: {
      0: {
        items: 1,
      },
      600: {
        items: 2,
      },
      1000: {
        items: 3,
      },
    },
  });
});
