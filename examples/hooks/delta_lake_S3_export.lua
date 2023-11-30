--[[
    action_args:
        - repo
        - commit_id

    export_delta_args:
        - table_paths: ["path/to/table1", "path/to/table2", ...]
        - lakefs_key
        - lakefs_secret
        - region

    storage_client:
        - put_object: function(bucket, key, data)
]]
local aws = require("aws")
local delta_export = require("lakefs/catalogexport/delta_exporter")

local sc = aws.s3_client(args.aws.aws_access_key_id, args.aws.aws_secret_access_key, args.aws.aws_region)

local delta_client = delta_export.get_delta_client(args.lakefs.access_key_id, args.lakefs.secret_access_key, args.aws.aws_region)
local delta_table_locations = delta_export.export_delta_log(action, args.table_paths, sc, delta_client)
for t, loc in pairs(delta_table_locations) do
    print("Delta Lake exported table \"" .. t .. "\"'s location: " .. loc .. "\n")
end
