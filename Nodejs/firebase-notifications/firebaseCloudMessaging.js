
var admin = require('firebase-admin')
var serviceAccount = require("./serviceAccount.json");
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://vietis-c69f8.firebaseio.com"
});

const sendFirebaseCloudMessage = async ({ title, data, notificationTokens }) => {
  const failedTokens = [];
  try {            
    if (notificationTokens.length == 0) {
      console.log('No notification Tokens to send');
      return []
    }    
    let response = await admin.messaging().sendMulticast({
      data,
      tokens: notificationTokens,
    })    
    if (response.failureCount > 0) {
      if (response.successCount == notificationTokens.length) {
        console.log("send all notifications successfully")
      }
      response.responses.forEach((resp, idx) => {
        if (!resp.success) {
          failedTokens.push(notificationTokens[idx]);
        }
      })
      console.log('List of tokens that caused failures: ');
      failedTokens.forEach(failedToken => {
        console.log(`{failedToken}\n`)
      })

    }    
    return failedTokens
  } catch (error) {    
    console.log('Cannot send FCM.Error =' + error);
    return failedTokens
  }
}

module.exports = {
    sendFirebaseCloudMessage
}
