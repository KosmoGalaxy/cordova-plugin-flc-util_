const exec = require('cordova/exec');

function FlcUtil() {}

FlcUtil.acquireWakeLock = function(a, b, c) {
  console.log('-- ' + a + ', ' + b + ', ' + c);
  if (a !== null && typeof a !== 'number') {
    FlcUtil.acquireWakeLock(null, a, b);
    return;
  }
  const timeout = a;
  const successCallback = b;
  const errorCallback = c;
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
