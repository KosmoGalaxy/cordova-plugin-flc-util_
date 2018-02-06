const exec = require('cordova/exec');

function FlcUtil() {}

FlcUtil.acquireWakeLock = function(timeout, successCallback, errorCallback) {
  exec(
    function() {
      if (successCallback) {
        successCallback();
      }
    },
    function(error) {
      if (errorCallback) {
        errorCallback(error);
      }
    },
    'FlcUtil',
    'acquireWakeLock',
    [timeout || null]
  );
};

FlcUtil.releaseWakeLock = function(successCallback, errorCallback) {
  exec(
    function() {
      if (successCallback) {
        successCallback();
      }
    },
    function(error) {
      if (errorCallback) {
        errorCallback(error);
      }
    },
    'FlcUtil',
    'releaseWakeLock'
  );
};

module.exports = FlcUtil;
