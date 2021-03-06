/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */

// This background script is needed to update the current tab
// and activate reader view.

let serializedDocs = new Map();

browser.runtime.onMessage.addListener((message, sender, sendResponse) => {
  switch (message.action) {
     case 'show':
       browser.tabs.update({url: `/readerview.html?url=${encodeURIComponent(message.url)}&id=${sender.contextId}`}).catch((e) => {
         console.error("Failed to open reader view", e, e.stack);
       });
       break;
     case 'addSerializedDoc':
        serializedDocs.set(sender.contextId.toString(), message.doc);
        break;
     case 'getSerializedDoc':
       let doc = serializedDocs.get(message.id);
       if (doc) {
         serializedDocs.delete(message.id);
       }
       sendResponse(doc);
       break;
     default:
       console.error(`Received unsupported action ${message.action}`);
   }
});
