/*
   Copyright 2019-2020 Kai Huebl (kai@huebl-sgh.de)

   Lizenziert gemäß Apache Licence Version 2.0 (die „Lizenz“); Nutzung dieser
   Datei nur in Übereinstimmung mit der Lizenz erlaubt.
   Eine Kopie der Lizenz erhalten Sie auf http://www.apache.org/licenses/LICENSE-2.0.

   Sofern nicht gemäß geltendem Recht vorgeschrieben oder schriftlich vereinbart,
   erfolgt die Bereitstellung der im Rahmen der Lizenz verbreiteten Software OHNE
   GEWÄHR ODER VORBEHALTE – ganz gleich, ob ausdrücklich oder stillschweigend.

   Informationen über die jeweiligen Bedingungen für Genehmigungen und Einschränkungen
   im Rahmen der Lizenz finden Sie in der Lizenz.

   Autor: Kai Huebl (kai@huebl-sgh.de)
 */

#ifndef __OpcUaWebServer_SubscriptionStatusNotify_h__
#define __OpcUaWebServer_SubscriptionStatusNotify_h__

#include <boost/property_tree/ptree.hpp>
#include "OpcUaStackCore/BuildInTypes/OpcUaString.h"

namespace OpcUaWebServer
{

	class SubscriptionStatusNotify
	: public OpcUaStackCore::JsonFormatter
	{
	  public:
		SubscriptionStatusNotify(void);
		virtual ~SubscriptionStatusNotify(void);

		uint32_t& subscriptionId(void);
		OpcUaStackCore::OpcUaString& subscriptionStatus(void);

      protected:
        bool jsonEncodeImpl(boost::property_tree::ptree& pt) const override;
        bool jsonDecodeImpl(const boost::property_tree::ptree& pt) override;

	  private:
		uint32_t subscriptionId_;
		OpcUaStackCore::OpcUaString subscriptionStatus_;
	};

}

#endif
