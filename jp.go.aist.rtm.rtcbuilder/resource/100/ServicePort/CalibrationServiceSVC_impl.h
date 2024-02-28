// -*-C++-*-
/*!
 * @file  CalibrationServiceSVC_impl.h
 * @brief Service implementation header of CalibrationService.idl
 *
 */

#include "CalibrationServiceSkel.h"

#ifndef CALIBRATIONSERVICESVC_IMPL_H
#define CALIBRATIONSERVICESVC_IMPL_H
 
/*!
 * @class ImageCalibService_CalibrationServiceSVC_impl
 * Example class implementing IDL interface ImageCalibService::CalibrationService
 */
class ImageCalibService_CalibrationServiceSVC_impl
 : public virtual POA_ImageCalibService::CalibrationService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~ImageCalibService_CalibrationServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   ImageCalibService_CalibrationServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~ImageCalibService_CalibrationServiceSVC_impl();

   // attributes and operations
   void setImageNumber(::CORBA::Short num);
   ::CORBA::Short getImageNumber();
   RTC::CameraImage captureCalibImage(::CORBA::Short num);
   RTC::CameraImage getCalibImage(::CORBA::Short num);
   ImageCalibService::ImageList* getCalibImages();
   ::CORBA::Boolean removeCalibImage(::CORBA::Short num);
   RTC::CameraInfo getCalibParameter();

};



#endif // CALIBRATIONSERVICESVC_IMPL_H


