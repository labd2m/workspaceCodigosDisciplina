package com.ufvmobile.pvanet.domain.repository;

import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.domain.model.Module;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 17/08/2016
 */
public interface ModuleRepository {
    @Nullable List<Module> getModules();

}
