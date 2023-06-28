insert into opc_ua_xml_filepath_table(id, xml_uuid, xml_filepath, xml_filename)
values(1, uuid('2D1EBC5B7D2741979CF0E84451C5BBB1'),  '~\\template.xml', 'template.xml')
on conflict do nothing;
