/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.feature.tabs.tabstray

import androidx.annotation.VisibleForTesting
import mozilla.components.browser.state.state.TabSessionState
import mozilla.components.browser.state.store.BrowserStore
import mozilla.components.concept.tabstray.TabsTray
import mozilla.components.feature.tabs.TabsUseCases
import mozilla.components.feature.tabs.ext.toTabs
import mozilla.components.support.base.feature.LifecycleAwareFeature

/**
 * Feature implementation for connecting a tabs tray implementation with the session module.
 */
class TabsFeature(
    tabsTray: TabsTray,
    private val store: BrowserStore,
    tabsUseCases: TabsUseCases,
    closeTabsTray: () -> Unit
) : LifecycleAwareFeature {
    @VisibleForTesting
    internal var presenter = TabsTrayPresenter(
        tabsTray,
        store,
        closeTabsTray
    )

    @VisibleForTesting
    internal var interactor = TabsTrayInteractor(
        tabsTray,
        tabsUseCases.selectTab,
        tabsUseCases.removeTab,
        closeTabsTray)

    override fun start() {
        presenter.start()
        interactor.start()
    }

    override fun stop() {
        presenter.stop()
        interactor.stop()
    }

    /**
     * Filter the list of tabs using [tabsFilter].
     *
     * @param tabsFilter A filter function returning `true` for all tabs that should be displayed in
     * the tabs tray.
     */
    fun filterTabs(tabsFilter: (TabSessionState) -> Boolean) {
        presenter.tabsFilter = tabsFilter

        val state = store.state
        presenter.updateTabs(state.toTabs(tabsFilter))
    }
}
