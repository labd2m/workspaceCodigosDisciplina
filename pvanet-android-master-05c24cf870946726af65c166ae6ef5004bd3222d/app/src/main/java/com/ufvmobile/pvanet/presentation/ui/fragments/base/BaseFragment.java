/*
 * Copyright 2015.  Emin Yahyayev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ufvmobile.pvanet.presentation.ui.fragments.base;

import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/**
 * Base class for all fragments.
 * unbind views,
 */
public abstract class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;


    @CallSuper
    @Override public void onDestroyView() {
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }
}
