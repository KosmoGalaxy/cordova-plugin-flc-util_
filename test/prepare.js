const cmd = require('node-cmd');
const fs = require('fs');
const path = require('path');

prepare();

function prepare() {
  copyDirectory('./android/app/src/main/java/pl/fulllegitcode/util', '../src/android/pl/fulllegitcode/util');
  cmd.get(
    'cd cordova && cordova plugin remove cordova-plugin-flc-util',
    (err, data, stderr) => {
      console.log(data);
      cmd.get(
        'cd cordova && cordova plugin add cordova-plugin-flc-util@1.1.0 --searchpath=../..',
        (err, data, stderr) => {
          if (err) {
            console.error(err);
            return;
          }
          if (stderr) {
            console.error(stderr);
            return;
          }
          console.log(data);
        }
      );
    }
  );
}

function copyDirectory(source, destination, files) {
  if (!fs.existsSync(destination)) {
    fs.mkdirSync(destination);
  }
  fs.readdirSync(source).forEach(fileName => {
    if (files && files.indexOf(fileName) === -1) {
      return;
    }
    const fileSource = path.join(source, fileName);
    const fileDestination = path.join(destination, fileName);
    const stats = fs.statSync(fileSource);
    if (stats.isDirectory()) {
      copyDirectory(fileSource, fileDestination);
    } else {
      fs.copyFileSync(fileSource, fileDestination);
    }
  });
}
