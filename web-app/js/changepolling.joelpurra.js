/*!
 * @license ChangePolling
 * Copyright © 2012 Joel Purra <http://joelpurra.se/>
 * Released under MIT, BSD and GPL license. Comply with at least one.
 *
 * A jQuery plugin to check if the value has changed in an input/select/textarea
 * without relying on the jQuery .change() event. This is perfomed using polling
 * at a configurable interval, which will then trigger the .change() event.
 */
// https://gist.github.com/2944926
//
// USAGE
// $("#my-input-to-poll").changePolling();
//
// DEFAULT OPTIONS
// $("#my-input-to-poll").changePolling({
//     interval: 1000,
//     autoStart: true
// });
//
/*jslint vars: true, white: true, browser: true*/
/*global jQuery, window*/

(function($, window) {
    "use strict"; // jshint ;_;
    var tag = "changePolling",
        ChangePolling = function(element, options) {
            this.$element = $(element);
            this.options = $.extend(true, {}, $.fn.changePolling.defaults, options);

            this.options.previousValue = this.$element.val();

            this.stop();

            if (this.options.autoStart === true) {
                this.start();
            }
        };

    ChangePolling.prototype = {

        constructor: ChangePolling

        ,
        start: function() {
            this.options.intervalId = window.setInterval($.proxy(this.checkForChange, this), this.options.interval);
        }

        ,
        stop: function() {
            window.clearInterval(this.options.intervalId);

            this.options.intervalId = null;
        }

        ,
        checkForChange: function() {
            var currentValue = this.$element.val();

            if (this.options.previousValue !== currentValue) {
                // Trigger change event - "this is where the magic happens"
                this.$element.change();
            }

            this.options.previousValue = currentValue;
        }
    };

    $.fn.extend({
        changePolling: function(option) {
            return this.each(function() {
                var $this = $(this),
                    data = $this.data(tag),
                    options = typeof option === "object" && option;

                if (!data) {
                    $this.data(tag, (data = new ChangePolling(this, options)));
                }

                if (typeof option === "string") {
                    data[option]();
                }
            });
        }
    });

    $.fn.changePolling.defaults = {
        interval: 1000,
        autoStart: true
    };

    $.fn.changePolling.Constructor = ChangePolling;

}(jQuery, window));