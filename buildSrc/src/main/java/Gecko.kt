/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

internal object GeckoVersions {
    /**
     * GeckoView Nightly Version.
     */

    const val nightly_version = "73.0.20200106092427"

    /**
     * GeckoView Beta Version.
     */
    const val beta_version = "72.0.20200107212822"

    /**
     * GeckoView Release Version.
     */
    const val release_version = "71.0.20200108003105"
}

@Suppress("MaxLineLength")
object Gecko {
    const val geckoview_nightly = "org.mozilla.geckoview:geckoview-nightly:${GeckoVersions.nightly_version}"

    // Release branch only: Updating from 72.0.20191230122537 to 72.0.20200107212822 requires us to
    // switch from geckoview-beta to geckoview.
    const val geckoview_beta = "org.mozilla.geckoview:geckoview:${GeckoVersions.beta_version}"
    const val geckoview_release = "org.mozilla.geckoview:geckoview:${GeckoVersions.release_version}"
}
