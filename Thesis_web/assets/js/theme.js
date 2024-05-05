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
        $('body').attr('data-bs-theme', theme);
        localStorage.setItem('theme', theme);
        updateNavIcon(theme);

        updateActiveStateAndCheckmarks(theme);
    }

    function updateNavIcon(theme) {
        var themeIcon = $('.color-modes .dropdown-toggle i');
        themeIcon.removeClass('fa-sun fa-moon');
        themeIcon.addClass(theme === 'light' ? 'fa-sun' : 'fa-moon');
    }

    function updateActiveStateAndCheckmarks(theme) {
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
    // Detail Carousel with 
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


 /*--
        Isotpe
    -----------------------------------*/
    // var $isotopeGrid = $('.isotope-grid');
    // $isotopeGrid.imagesLoaded(function () {
    //     $isotopeGrid.isotope({
    //         itemSelector: '.grid-item',
    //         masonry: {
    //             columnWidth: '.grid-sizer'
    //         }
    //     });
    // });

    // var $isotopeFilter = $('.isotope-filter');
    // $isotopeFilter.on('click', 'button', function () {
    //     var $this = $(this),
    //         $filterValue = $this.attr('data-filter'),
    //         $targetIsotop = $this.parent().data('target');
    //     $this.addClass('active').siblings().removeClass('active');
    //     $($targetIsotop).isotope({
    //         filter: $filterValue
    //     });
    // });

    // // Column Toggle
    // $('.product-column-toggle').on('click', '.toggle', function (e) {
    //     e.preventDefault();
    //     var $this = $(this),
    //         $column = $this.data('column'),
    //         $prevColumn = $this.siblings('.active').data('column');
    //     $this.toggleClass('active').siblings().removeClass('active');
    //     $('.products').removeClass('row-cols-xl-' + $prevColumn).addClass('row-cols-xl-' + $column);
    //     $.fn.matchHeight._update();
    //     $('.isotope-grid').isotope('layout');
    // });

    // /*--
    //     Match Height
    // -----------------------------------*/
    // $('.isotope-grid .product').matchHeight();
    

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