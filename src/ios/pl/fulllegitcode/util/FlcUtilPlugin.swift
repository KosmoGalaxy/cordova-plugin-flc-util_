import Foundation

@objc(FlcUtilPlugin) class FlcUtilPlugin : CDVPlugin {
    
    func acquireWakeLock(command: CDVInvokedUrlCommand) {
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAsString: "ok"
        )
        
        self.commandDelegate!.sendPluginResult(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    func releaseWakeLock(command: CDVInvokedUrlCommand) {
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAsString: "ok"
        )
        
        self.commandDelegate!.sendPluginResult(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    func setKeepScreenOn(command: CDVInvokedUrlCommand) {
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAsString: "ok"
        )
        
        self.commandDelegate!.sendPluginResult(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    func decodeImage(command: CDVInvokedUrlCommand) {
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAsString: "ok"
        )
        
        self.commandDelegate!.sendPluginResult(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    func getIp(command: CDVInvokedUrlCommand) {
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAsString: "0.0.0.47"
        )
        
        self.commandDelegate!.sendPluginResult(
            pluginResult,
            callbackId: command.callbackId
        )
    }

}
