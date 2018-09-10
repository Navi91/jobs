package com.dkrasnov.jobs.dagger

import com.dkrasnov.jobs.dagger.common.DatabaseModule
import com.dkrasnov.jobs.dagger.common.RetrofitModule
import dagger.Module

@Module(includes = [DatabaseModule::class, RetrofitModule::class])
class JobsModule {
}