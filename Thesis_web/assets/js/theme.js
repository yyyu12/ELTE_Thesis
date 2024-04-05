"use strict";

$(function () {

    // ------------------------------------------------------- //
    //   Theme Color --- Light/Dark Mode
    // ------------------------------------------------------ //
    var savedTheme = localStorage.getItem('theme') || 'light';
    setTheme(savedTheme);

    $('.dropdown-item').on('click', function () {
        var themeValue = $(this).data('bs-theme-value');
        setTheme(themeValue);
    });

    function setTheme(theme) {
        // 设置主题颜色
        $('body').attr('data-bs-theme', theme);
        // 保存主题到localStorage
        localStorage.setItem('theme', theme);

        // 更新导航栏图标
        updateNavIcon(theme);

        // 更新激活状态和勾选图标
        updateActiveStateAndCheckmarks(theme);
    }

    function updateNavIcon(theme) {
        var themeIcon = $('.color-modes .dropdown-toggle i');
        // 根据当前主题设置图标
        themeIcon.removeClass('fa-sun fa-moon');
        themeIcon.addClass(theme === 'light' ? 'fa-sun' : 'fa-moon');
    }

    function updateActiveStateAndCheckmarks(theme) {
        // 遍历所有的dropdown-item
        $('.dropdown-item').each(function () {
            var itemTheme = $(this).data('bs-theme-value');
            var isActive = itemTheme === theme;
            $(this).toggleClass('active', isActive)
                .attr('aria-pressed', isActive)
                .find('.fa-check').toggleClass('d-none', !isActive);
        });
    }


    // ------------------------------------------------------- //
    //   GLightbox
    // ------------------------------------------------------ //

    const lightbox = GLightbox({
        touchNavigation: true,
        //loop: true,
        autoplayVideos: true,
    });

    // ------------------------------------------------------- //
    //   Make a sticky navbar on scrolling
    // ------------------------------------------------------ //
    $(window).scroll(function () {
        var body = $("body"),
            stickyNavbar = $("nav.navbar-sticky"),
            header = $(".header"),
            topbar = $(".top-bar");

        function makeItFixed(x) {
            if ($(window).scrollTop() > x) {
                stickyNavbar.addClass("fixed-top");
                if (!header.hasClass("header-absolute")) {
                    body.css("padding-top", stickyNavbar.outerHeight());
                    body.data("smooth-scroll-offset", stickyNavbar.outerHeight());
                }
            } else {
                stickyNavbar.removeClass("fixed-top");
                body.css("padding-top", "0");
            }
        }

        // if .top-bar exists, affix the navbar when we scroll past .top-bar
        // else affix it immediately
        if (stickyNavbar.length > 0 && topbar.length > 0) {
            makeItFixed(topbar.outerHeight());
        } else if (stickyNavbar.length > 0) {
            makeItFixed(0);
        }
    });

    // ------------------------------------------------------- //
    //   Increase/Decrease product amount
    // ------------------------------------------------------ //

    $(".btn-items-decrease").on("click", function () {
        var input = $(this).siblings(".input-items");
        if (parseInt(input.val(), 10) >= 1) {
            input.val(parseInt(input.val(), 10) - 1);
        }
    });

    $(".btn-items-increase").on("click", function () {
        var input = $(this).siblings(".input-items");
        input.val(parseInt(input.val(), 10) + 1);
    });

    // ------------------------------------------------------- //
    //   Scroll to top button
    // ------------------------------------------------------ //

    $(window).on("scroll", function () {
        if ($(window).scrollTop() >= 800) {
            $("#scrollTop").fadeIn();
        } else {
            $("#scrollTop").fadeOut();
        }
    });

    $("#scrollTop").on("click", function () {
        $("html, body").animate(
            {
                scrollTop: 0,
            },
            1000
        );
    });

    
    // ------------------------------------------------------- //
    //   Image zoom
    // ------------------------------------------------------ //

    $('[data-bs-toggle="zoom"]').each(function () {
        $(this).zoom({
            url: $(this).attr("data-image"),
            duration: 0,
        });
    });


    // ------------------------------------------------------- //
    //  Button-style form labels used in detail.html
    // ------------------------------------------------------ //

    $(".detail-option-btn-label").on("click", function () {
        var button = $(this);

        button.parents(".detail-option").find(".detail-option-btn-label").removeClass("active");

        button.toggleClass("active");
    });

    // ------------------------------------------------------- //
    //   Product Slider
    // ------------------------------------------------------ //
    $(".product-slider").owlCarousel({
        loop: true, // 循环播放
        margin: 0,
        nav: true,
        dots: true,
        navText: [
            "<i class='fa-solid fa-arrow-left'></i>",
            "<i class='fa-solid fa-arrow-right'></i>"
        ],
        smartSpeed: 400,
        autoplay: true, // 开启自动播放
        autoplayTimeout: 1500, // 设置自动播放之间的间隔时间（单位：毫秒）
        autoplayHoverPause: true, // 当鼠标悬停时暂停自动播放
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },
            600: {
                items: 2,
            },
            1000: {
                items: 5,
            },
        },
    });


    // ------------------------------------------------------- //
    // Detail Carousel with thumbs
    // ------------------------------------------------------ //
    $(".detail-slider").owlCarousel({
        loop: true,
        items: 1,
        thumbs: true,
        thumbsPrerendered: true,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
                dots: true,
            },
            768: {
                items: 1,
                dots: false,
            },
        },
    });

    // ------------------------------------------------------- //
    // Detail Full Carousel
    // ------------------------------------------------------ //
    $(".detail-full").owlCarousel({
        loop: true,
        items: 1,
        dots: true,
        responsiveClass: false,
    });

    // ------------------------------------------------------- //
    // Brands Slider
    // ------------------------------------------------------ -//

    var brandsSlider = $(".brands-slider");
    brandsSlider.owlCarousel({
        loop: true,
        margin: 20,
        dots: true,
        nav: false,
        smartSpeed: 400,
        responsiveClass: true,
        responsive: {
            0: {
                items: 2,
            },
            600: {
                items: 3,
            },
            1000: {
                items: 6,
                loop: false,
            },
        },
        onRefresh: function () {
            brandsSlider.find(".item").height("");
        },
        onRefreshed: function () {
            var maxHeight = 0;
            var items = brandsSlider.find(".item");
            items.each(function () {
                var itemHeight = $(this).height();
                if (itemHeight > maxHeight) {
                    maxHeight = itemHeight;
                }
            });
            if (maxHeight > 0) {
                items.height(maxHeight);
            }
        },
    });

    /*--
        Match Height
    -----------------------------------*/

    // Column Toggle
    $('.product-column-toggle').on('click', '.toggle', function (e) {
        e.preventDefault();
        var $this = $(this),
            $column = $this.data('column'),
            $prevColumn = $this.siblings('.active').data('column');
        $this.toggleClass('active').siblings().removeClass('active');
        $('.products').removeClass('row-cols-xl-' + $prevColumn).addClass('row-cols-xl-' + $column);
        $.fn.matchHeight._update();
        $('.isotope-grid').isotope('layout');
    });

    /*--
    Isotpe
    
    var $isotopeGrid = $('.isotope-grid');
    var $isotopeFilter = $('.isotope-filter');
    $isotopeGrid.imagesLoaded(function () {
        $isotopeGrid.isotope({
            itemSelector: '.grid-item',
            masonry: {
                columnWidth: '.grid-sizer'
            }
        });
    });
    $isotopeFilter.on('click', 'button', function () {
        var $this = $(this),
            $filterValue = $this.attr('data-filter'),
            $targetIsotop = $this.parent().data('target');
        $this.addClass('active').siblings().removeClass('active');
        $($targetIsotop).isotope({
            filter: $filterValue
        });
    }); -----------------------------------*/

    // ------------------------------------------------------- //
    //   Bootstrap tooltips
    // ------------------------------------------------------- //

    $('[data-bs-toggle="tooltip"]').tooltip();

    // ------------------------------------------------------- //
    //   Smooth Scroll
    // ------------------------------------------------------- //

    var smoothScroll = new SmoothScroll("a[data-smooth-scroll]", {
        offset: 80,
    });

    // ------------------------------------------------------- //
    //   Object Fit Images
    // ------------------------------------------------------- //

    objectFitImages();

});